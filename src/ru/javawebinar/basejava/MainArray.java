package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for ru.javawebinar.basejava.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new ArrayStorage(); //ListStorage(); //

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid fullname | delete uuid | get uuid | update newname | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            String fullName = null;
            if (params.length >= 2) {
                uuid = params[1].intern();
                try {
                    fullName = params[2].intern();
                } catch (Exception e) {
                    fullName = "";
                }
            }
            /*if (params.length >= 3) {
                fullName = params[2].intern();
            }*/
            switch (params[0]) {
                case "list":
                    printAllSorted();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(uuid, fullName);
                    ARRAY_STORAGE.save(r);
                    printAllSorted();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(uuid);
                    printAllSorted();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAllSorted();
                    break;
                case "update":
                    r = new Resume(uuid, fullName);
                    ARRAY_STORAGE.update(r);
                    printAllSorted();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    /*static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }*/

    static void printAllSorted() {
        List<Resume> all = ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}