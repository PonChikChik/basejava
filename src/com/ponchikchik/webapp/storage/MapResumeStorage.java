package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object findSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey, String uuid) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected List<Resume> doCopyAllResumes() {
        return new ArrayList<>(storage.values());
    }
}
