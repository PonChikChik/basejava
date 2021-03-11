package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ponchikchik.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private final Storage storage;
    private int size = 0;

    protected AbstractStorageTest(Storage storage) {
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

        assetsSize();
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);

        assetsResume(resume);
        assetsSize();
    }

    @Test
    public void updateResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);

        assetsResume(resume);
        size++;
        assetsSize();
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
        assetsResume(new Resume(UUID_1));
        assetsResume(new Resume(UUID_2));
        assetsResume(new Resume(UUID_3));
    }

    @Test
    public void getResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        size--;
        assetsSize();

        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void getAll() {
        Resume[] allResumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};

        assertArrayEquals(allResumes, storage.getAll());
    }

    @Test
    public void size() {
        assetsSize();
    }

    private void assetsSize() {
        assertEquals(size, storage.size());
    }

    private void assetsResume(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
