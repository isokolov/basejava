package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * gkislin
 * 21.07.2016
 */
public class MainFile {

    private static void printFiles(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    System.out.println(file.getName() + " is a directory");
                    printFiles(file);
                }
                System.out.println(file.getName());
            }
        }
        System.out.println("End of directory " + directory.getName());
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
        for (File theFile : Objects.requireNonNull(files)) {
            if (theFile.isDirectory()) {
                System.out.println(theFile.getName() + " is a directory");
                printFiles(theFile);
                continue;
            }

            System.out.println(theFile.getName());

        }

        /*File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }*/

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
