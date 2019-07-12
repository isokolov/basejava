package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new TreeMap<>();

    @Override
    protected String getSearchKey(String searchKey) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getValue().getUuid().equals(searchKey)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean resumeNotExists(Object searchKey) {
        return searchKey == null;
    }

    @Override
    protected void toSave(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume toGet(Object searchKey) {
        String index = getSearchKey(searchKey.toString());
        if (index != null) {
            return map.get(index);
        }
        return null;
    }

    @Override
    protected void toUpdate(Resume resume, Object searchKey) {
        map.put(searchKey.toString(), resume);
    }

    @Override
    protected void toDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
