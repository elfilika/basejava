package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> stlist = new ArrayList<>();

    @Override
    public void clear() {
        stlist.clear();
    }

    @Override
    public Resume[] getAll() {
        return stlist.toArray(new Resume[stlist.size()]);
    }

    @Override
    public int size() {
        return stlist.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < stlist.size(); i++) {
            if (stlist.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        Integer index = (Integer) searchKey;
        stlist.set(index.intValue(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        stlist.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        Integer index = (Integer) searchKey;
        return stlist.get(index.intValue());
    }

    @Override
    protected void doDelete(Object searchKey) {
        Integer index = (Integer) searchKey;
        stlist.remove(index.intValue());
    }
}
