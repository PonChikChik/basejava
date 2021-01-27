/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }

        size = 0;
    }

    void save(Resume resume) {
        storage[size] = resume;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];

            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }

        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];

            if (resume.uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - 1);
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];

        if (size >= 0) System.arraycopy(storage, 0, result, 0, size);

        return result;
    }

    int size() {
        return size;
    }
}
