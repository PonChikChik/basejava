/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    void clear() {
        for (int i = 0; i < size; ++i) {
            storage[i] = null;
        }

        size = 0;
    }

    void save(Resume resume) {
        storage[size] = resume;
        ++size;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }

            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }

        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; ++i) {
            Resume resume = storage[i];

            if (resume.uuid.equals(uuid)) {
                this.storage = this.changeIndexes(storage, i);
                --size;
                break;
            }
        }
    }

    Resume[] changeIndexes(Resume[] resumes, int index) {
        resumes[index] = resumes[index + 1];

        for (int i = index + 1; i < resumes.length; ++i) {
            if (i == resumes.length - 1) {
                resumes[i] = null;
            } else {
                resumes[i] = resumes[i + 1];
            }

        }

        return resumes;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];

        for (int i = 0; i < size; ++i) {
            result[i] = storage[i];
        }

        return result;
    }

    int size() {
        return size;
    }
}
