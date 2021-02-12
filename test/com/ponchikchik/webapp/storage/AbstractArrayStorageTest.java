package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.ponchikchik.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private final Storage storage;
    private int size = 0;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

        size += 3;
    }

    @Test
    public void clear() {
        storage.clear();
        size = 0;

        assetsStorageSize(size);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);

        assetsStorageResume(resume);
        assetsStorageSize(size);
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);

        assetsStorageResume(resume);
        size++;
        assetsStorageSize(size);
    }

    @Test
    public void get() {
        assetsStorageResume(new Resume(UUID_1));
        assetsStorageResume(new Resume(UUID_2));
        assetsStorageResume(new Resume(UUID_3));
    }

    @Test
    public void delete() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_1);
            size--;
            assetsStorageSize(size);
            storage.get(UUID_1);
        });
    }

    @Test
    public void getAll() {
        Resume[] allResumes = storage.getAll();

        for (Resume resume : allResumes) {
            assetsStorageResume(resume);
        }
    }

    @Test
    public void size() {
        assetsStorageSize(size);
    }

    @Test
    public void getResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void deleteResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void updateResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void saveResumeExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void assertStorageOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                int counter = size;

                while (counter != STORAGE_LIMIT) {
                    storage.save(new Resume());
                    counter++;
                }
            } catch (StorageException e) {
                Assertions.fail("Storage overflow too early", e);
            }

            storage.save(new Resume());
            storage.clear();
        });
    }

    private void assetsStorageSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assetsStorageResume(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
