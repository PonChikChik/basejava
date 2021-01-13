/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; ++i) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume resume) {
        for (int i = 0; i < storage.length; ++i) {
            if (storage[i] == null) {
                storage[i] = resume;
                break;
            }
        }
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

        Resume emptyResume = new Resume();
        emptyResume.uuid = "null";

        return emptyResume;
    }

    void delete(String uuid) {
        for (int i = 0; i < this.size(); ++i) {
            Resume resume = storage[i];

            if (resume.uuid.equals(uuid)) {
                this.storage = this.changeIndexes(storage, i);
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
        return storage;
    }

    int size() {
        int result = 0;

        for(Resume resume : storage) {
            if (resume == null) {
                break;
            }

            ++result;
        }

        return result;
    }
}
