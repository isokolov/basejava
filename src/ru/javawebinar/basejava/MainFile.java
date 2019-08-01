package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class MainFile {

    private static void printFiles(File directory) {
        if (directory != null) {
            System.out.println(directory.getName() + " - is a directory");
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
                if (file.isDirectory()) {
                    System.out.println(file.getName());
                    printFiles(file);
                }

            }
        }

    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File theFile : files) {
                if (theFile.isFile()) {
                    System.out.println(theFile.getName());
                } else if (theFile.isDirectory()) {
                    printFiles(theFile);
                }

            }
        }

    }

}
