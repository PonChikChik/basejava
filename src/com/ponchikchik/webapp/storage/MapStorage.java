package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<Integer, Resume> storage = new HashMap<>();
    private int mapKey = 0;

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[]{});
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int findResumeIndex(String uuid) {
        for (Map.Entry<Integer, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }

        return -1;
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.put(index, resume);
    }

    @Override
    protected void doSave(int index, Resume resume) {
        storage.put(mapKey, resume);
        mapKey++;
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        storage.remove(index);
        mapKey--;
    }
}
