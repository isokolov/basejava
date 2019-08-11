package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        printDirectoryDeeply(dir, "");
    }

    // TODO: make pretty output
    public static void printDirectoryDeeply(File dir, String offset) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(offset + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(offset + "Directory: " + file.getName());
                    printDirectoryDeeply(file, offset + "  ");
                    System.out.println(offset + "End of Directory: " + file.getName());
                }
            }
        }
    }
}