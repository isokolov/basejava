package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Storage is full. New resumes can't be added");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            System.out.println("Resume " + resume.getUuid() + " is already in the storage");
        } else if (index == -1) {
            storage[size] = resume;
            size++;
            System.out.println("Resume " + resume.getUuid() + " added to the storage");
        }

    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            System.out.println("Resume " + resume.getUuid() + " is was updated in the storage");
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not in the storage");
        }

    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            System.out.println("Resume " + uuid + " is in the storage");
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " is not in the storage");
            return null;
        }

    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = null;
            for (int j = index; j < size; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
            System.out.println("Resume " + uuid + " was deleted from the storage");
        } else {
            System.out.println("Resume " + uuid + " is not in the storage");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        if (storage.length == 0) return null;

        Resume[] resumes = new Resume[size];
        resumes = Arrays.copyOfRange(storage, 0, size);

        return resumes;

    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}
