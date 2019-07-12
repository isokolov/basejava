package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> resumes = new ArrayList<>();

    public void ListStorage(List<Resume> resumes) {
        this.resumes = resumes;
    }

    @Override
    public Integer getSearchKey(String searchKey) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getUuid().equals(searchKey)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean resumeNotExists(Object searchKey) {
        return searchKey == null;
    }

    @Override
    public void toSave(Resume resume, Object searchKey) {
        if (searchKey != null) {
            resumes.add((Integer) searchKey, resume);
        } else {
            resumes.add(resumes.size(), resume);
        }
    }

    @Override
    public Resume toGet(Object searchKey) {
        return resumes.get((Integer) searchKey);
    }

    @Override
    public void toUpdate(Resume resume, Object searchKey) {
        resumes.set((Integer) searchKey, resume);
    }

    @Override
    public void toDelete(Object searchKey) {
        resumes.remove(((Integer) searchKey).intValue());
    }

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(new Resume[resumes.size()]);
    }

    @Override
    public int size() {
        return resumes.size();
    }
}
