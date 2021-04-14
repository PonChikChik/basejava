package com.ponchikchik.webapp.storage;

import com.ponchikchik.webapp.exception.StorageException;
import com.ponchikchik.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ponchikchik.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void assertStorageOverflow() {
        try {
            int counter = 3;

            while (counter != STORAGE_LIMIT) {
                storage.save(new Resume("Name" + counter));
                counter++;
            }
        } catch (StorageException e) {
            Assertions.fail("Storage overflow too early", e);
        }

        assertThrows(StorageException.class, () -> storage.save(new Resume("Overflow Name")));
    }
}
