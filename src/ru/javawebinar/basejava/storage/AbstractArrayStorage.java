package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("ERROR: Resume " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ERROR: Resume " + r.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage overflow");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void fillDeletedElement(int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}
