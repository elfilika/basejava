package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    //Resume[] getAll();

    //  return list, sorted by name
    List<Resume> getAllSorted();

    int size();
}
