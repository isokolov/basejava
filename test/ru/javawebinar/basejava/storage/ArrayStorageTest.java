package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.model.Resume;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private ArrayStorage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME1 = new Resume(UUID_1);
    private static final Resume RESUME2 = new Resume(UUID_2);
    private static final Resume RESUME3 = new Resume(UUID_3);

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @BeforeClass
    public static void beforeTest() {
        System.out.println("Test for ArrayStorage started...");
    }

    @AfterClass
    public static void aftrTest() {
        System.out.println("Test for ArrayStorage finished...");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);

    }

    @Test
    public void getIndex() {
        Assert.assertEquals(0, storage.getIndex(UUID_1));
        storage.delete(UUID_2);
        Assert.assertEquals(1, storage.getIndex(UUID_3));
    }
}