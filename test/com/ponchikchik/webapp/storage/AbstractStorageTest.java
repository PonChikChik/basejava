package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.ResumeTestData;
import com.ponchikchik.webapp.exception.ExistStorageException;
import com.ponchikchik.webapp.exception.NotExistStorageException;
import com.ponchikchik.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("storage/");
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String NAME_1 = "name1";
    private static final String NAME_2 = "name2";
    private static final String NAME_3 = "name3";
    private static final String NAME_4 = "name4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    protected final Storage storage;

    static {
        RESUME_1 = ResumeTestData.createResume(UUID_1, NAME_1);
        RESUME_2 = ResumeTestData.createResume(UUID_2, NAME_2);
        RESUME_3 = ResumeTestData.createResume(UUID_3, NAME_3);
        RESUME_4 = ResumeTestData.createResume(UUID_4, NAME_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @AfterEach
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();

        assertSize(0);
    }

    @Test
    public void update() {
        Resume resume = ResumeTestData.createResume(UUID_1, "Updated Name");
        storage.update(resume);

        assertResume(resume);
        assertSize(3);
    }

    @Test
    public void updateResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void save() {
        Resume resume = RESUME_4;
        storage.save(resume);

        assertResume(resume);
        assertSize(4);
    }

    @Test
    public void saveResumeExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void get() {
        assertResume(RESUME_1);
        assertResume(RESUME_2);
        assertResume(RESUME_3);
    }

    @Test
    public void getResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);

        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteResumeNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void getAllSorted() {
        List<Resume> sortedResumes = storage.getAllSorted();
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);

        assertEquals(sortedResumes, expectedResumes);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertResume(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}
