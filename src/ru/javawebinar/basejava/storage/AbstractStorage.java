package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String searchKey);

    protected abstract boolean resumeNotExists(Object searchKey);

    protected abstract void toSave(Resume resume, Object searchKey);

    protected abstract Resume toGet(Object searchKey);

    protected abstract void toUpdate(Resume resume, Object searchKey);

    protected abstract void toDelete(Object searchKey);

    @Override
    public void update(Resume r) {
        Object index = getSearchKey(r.getUuid());
        if (resumeNotExists(index)) {
            throw new NotExistStorageException(r.getUuid());
        }
        toUpdate(r, index);
    }

    @Override
    public void save(Resume r) {
        Object index = getSearchKey(r.getUuid());
        if (!resumeNotExists(index)) {
            throw new ExistStorageException(r.getUuid());
        }
        toSave(r, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getSearchKey(uuid);
        if (resumeNotExists(index)) {
            throw new NotExistStorageException(uuid);
        }
        return toGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getSearchKey(uuid);
        if (resumeNotExists(index)) {
            throw new NotExistStorageException(uuid);
        }
        toDelete(index);
    }

}
