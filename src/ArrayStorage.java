/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for(int i=0; i<size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        /* add element */
        storage[size] = r;
        size = size + 1;
    }

    Resume get(String uuid) {
        for(int i=0; i<size; i++) {
            if(storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for(int i=0; i<size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size = size - 1;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] rout = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, rout, 0, size);
        return rout;
    }

    int size() {
        return size;
    }

}
