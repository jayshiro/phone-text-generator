package com.jayjay.service;

import com.jayjay.exception.InvalidFileExtensionException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtReaderImpl implements TxtReader {


    public List<String> readTxt(String filePath) throws InvalidFileExtensionException, IOException {
        List<String> result = new ArrayList<>();

        if(!hasValidFileExtension(filePath)) {
            throw new InvalidFileExtensionException("File has an invalid extension. Please use txt files only.");
        }

        String row="";
        try (FileReader fileReader = new FileReader(filePath)) {

            try (BufferedReader br = new BufferedReader(fileReader)) {
                while ((row = br.readLine()) != null) {

                    result.add(row);
                }
            }

        } catch (IOException e) {
            throw new IOException("File is missing. Please provide correct file path.");
        }

        return result;
    }

    private boolean hasValidFileExtension(String filePath) {
        int index = filePath.lastIndexOf('.');
        if(index > 0) {
            if(filePath.substring(index + 1).equals(EXTENSION_TXT)) {
                return true;
            }
        }
        return false;
    }
}
