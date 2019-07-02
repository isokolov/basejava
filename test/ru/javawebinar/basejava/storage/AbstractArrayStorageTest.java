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
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void update_notExist() {
        storage.update(new Resume("ddd"));
    }

    @Test
    public void update_exist() {
        storage.update(RESUME1);
        Assert.assertEquals("uuid1", RESUME1.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(3, resumes.length);
        Assert.assertEquals(RESUME1, resumes[0]);
        Assert.assertEquals(RESUME2, resumes[1]);
        Assert.assertEquals(RESUME3, resumes[2]);
    }

    @Test(expected = ExistStorageException.class)
    public void save_exist() {
        storage.save(RESUME1);
    }

    @Test
    public void save_new() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals("uuid4", storage.get("uuid4").getUuid());
    }

    @Test(expected = StorageException.class)
    public void save_overflow() {
        for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT ; i++) {
            storage.save(new Resume("uu" + i));
        }
        storage.save(new Resume("overflow Resume"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_not_exist() {
        storage.delete("ddd");
    }

    @Test
    public void delete_exist() {
        storage.delete(RESUME3.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume("uuid1").getUuid(), storage.get("uuid1").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void get_NotExist() throws Exception {
        storage.get("dummy");
    }

}
