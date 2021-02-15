package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.ponchikchik.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

        size = 3;
    }

    @AfterEach
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        size = 0;

        assetsStorageSize();
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);

        assetsStorageResume(resume);
        assetsStorageSize();
    }

    @Test
    public void updateResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);

        assetsStorageResume(resume);
        size++;
        assetsStorageSize();
    }

    @Test
    public void saveResumeExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void assertStorageOverflow() {
        try {
            int counter = size;

            while (counter != STORAGE_LIMIT) {
                storage.save(new Resume());
                counter++;
            }
        } catch (StorageException e) {
            Assertions.fail("Storage overflow too early", e);
        }

        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    public void get() {
        assetsStorageResume(new Resume(UUID_1));
        assetsStorageResume(new Resume(UUID_2));
        assetsStorageResume(new Resume(UUID_3));
    }

    @Test
    public void getResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void delete() {
        size--;
        storage.delete(UUID_1);
        assetsStorageSize();

        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
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
        assetsStorageSize();
    }

    private void assetsStorageSize() {
        assertEquals(size, storage.size());
    }

    private void assetsStorageResume(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
