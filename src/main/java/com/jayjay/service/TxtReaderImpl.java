package com.jayjay.service;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtReaderImpl implements TxtReader {


    public List<String> readTxt(String filePath) throws InvalidFileExtensionException, IOException, EmptyFileException {
        List<String> result = new ArrayList<>();

        if(!hasValidFileExtension(filePath)) {
            throw new InvalidFileExtensionException("File has an invalid extension. Please use txt files only.");
        }

        String row="";
        try (FileReader fileReader = new FileReader(filePath)) {

            try (BufferedReader br = new BufferedReader(fileReader)) {
                while ((row = br.readLine()) != null) {
                    row = removeUnwantedCharacters(row);
                    if(row.length() > 0) {
                        result.add(row);
                    }
                }
            }

        } catch (IOException e) {
            throw new IOException("File is missing. Please provide correct file path.");
        }

        if(result.size() == 0) {
            throw new EmptyFileException(filePath + " doesn't have any valid content. Please choose a different file.");
        }

        return result;
    }

    @Override
    public String removeUnwantedCharacters(String text) {
        return text.replaceAll("[^A-Za-z0-9]", "");
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
