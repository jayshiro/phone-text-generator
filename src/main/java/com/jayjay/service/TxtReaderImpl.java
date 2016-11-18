package com.jayjay.service;

import com.jayjay.exception.InvalidFileExtensionException;

import java.util.List;

public class TxtReaderImpl implements TxtReader {


    public List<String> readTxt(String filePath) throws InvalidFileExtensionException {
        if(!hasValidFileExtension(filePath)) {
            throw new InvalidFileExtensionException("File has an invalid extension. Please use txt files only.");
        }
        return null;
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
