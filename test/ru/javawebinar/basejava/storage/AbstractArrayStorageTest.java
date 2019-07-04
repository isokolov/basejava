package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("ddd"));
    }

    @Test
    public void updateExist() throws Exception {
        Resume newResume = new Resume("uuid1");
        storage.update(newResume);
        Assert.assertTrue(newResume == storage.get("uuid1"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Resume[] newResumes = new Resume[3];
        newResumes[0] = RESUME1;
        newResumes[1] = RESUME2;
        newResumes[2] = RESUME3;
        Assert.assertArrayEquals(resumes, newResumes);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME1);
    }

    @Test
    public void saveNew() throws Exception {
        Resume RESUME4 = new Resume("uuid4");
        storage.save(RESUME4);
        Assert.assertEquals(RESUME4, storage.get(RESUME4.getUuid()));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uu" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("overflow Resume"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("ddd");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteExist() throws Exception {
        storage.delete(RESUME3.getUuid());
        Assert.assertEquals(2, storage.size());
        storage.get(RESUME3.getUuid());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME1, storage.get(RESUME1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        Resume RESUME4 = new Resume("dummy");
        storage.get(RESUME4.getUuid());
    }

}
