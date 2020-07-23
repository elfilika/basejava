package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("ERROR: Resume " + resume.getUuid() + " already exist");
        } else if (size == storage.length) {
            System.out.println("ERROR: Storage overflow");
        } else {
            //add element with sort
            index = (index * -1) - 1; //binary search returns (-index_to_insert - 1)
            //move tail
            for (int i = size; i > index; i--) {
                storage[i] = storage[i - 1];
            }
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume " + uuid + " not exist");
        } else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            //storage[size] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
