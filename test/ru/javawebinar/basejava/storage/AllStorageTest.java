package ru.javawebinar.basejava.storage;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
ArrayStorageTest.class,
ListStorageTest.class,
SortedArrayStorageTest.class,
MapUuidStorageTest.class
})
public class AllStorageTest {
}
