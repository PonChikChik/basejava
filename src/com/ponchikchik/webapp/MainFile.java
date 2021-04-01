package com.ponchikchik.webapp;

import java.io.File;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("./src/com/ponchikchik/webapp");

        printDirectory(dir);
    }

    public static void printDirectory(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            Arrays.sort(files);

            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("\nDirectory: " + file.getName());
                    printDirectory(file);
                }
            }
        }
    }
}
