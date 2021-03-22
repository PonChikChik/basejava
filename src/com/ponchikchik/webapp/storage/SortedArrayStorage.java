package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Object findSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, ""), RESUME_COMPARATOR);
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected void insertResume(int index, Resume resume) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);

        storage[insertIndex] = resume;
    }
}
