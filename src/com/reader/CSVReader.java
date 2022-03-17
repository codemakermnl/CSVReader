package com.reader;


import com.reader.exception.FileNotCSVException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static final String EMPTY = "";

    public static List<String> readLines(final String filepath) throws IOException, FileNotCSVException {
        File file = new File(filepath);
        if( !file.exists() ) {
            throw new FileNotFoundException("File does not exist.");
        }

        if( file.isDirectory() ) {
            throw new FileNotFoundException("Specified file is not a file but a directory");
        }

        if( !getFileExtension(file).toLowerCase().contains("csv") ) {
            throw new FileNotCSVException("Given file is not a CSV file.");
        }

        return readLines(file);
    }

    private static List<String> readLines(final File file) throws IOException {
        List<String> rows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            if(isEmpty(line)) {
                rows.add(EMPTY);
                continue;
            }

            rows.add(line);
        }

        return rows;
    }

    private static boolean isEmpty(final String line) {
        return line.replaceAll(",", "").trim().isEmpty();
    }

    private static String getFileExtension(final File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return EMPTY;
        }
        return name.substring(lastIndexOf+1);
    }
}