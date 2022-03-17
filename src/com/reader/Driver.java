package com.reader;

import com.reader.exception.FileNotCSVException;

import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter absolute path of file: ");
        String filepath = sc.nextLine();

        try {
            CSVReader.readLines(filepath).forEach(System.out::println);
        } catch (IOException | FileNotCSVException e) {
            System.err.println(e.getMessage());
        }
    }
}