package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);

        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void removeItem(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1);
    }

    @Override
    protected void insertItem(int index, Resume resume) {
        int insertIndex = -index - 1;
        storage[insertIndex] = resume;
    }
}
