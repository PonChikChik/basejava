package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[]{});
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int findResumeIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void doSave(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        storage.remove(index);
    }
}
