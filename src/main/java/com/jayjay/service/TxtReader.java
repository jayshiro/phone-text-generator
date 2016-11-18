package com.jayjay.service;

import com.jayjay.exception.InvalidFileExtensionException;

import java.util.List;

public interface TxtReader {
    public static final String EXTENSION_TXT = "txt";

    public List<String> readTxt(String filePath) throws InvalidFileExtensionException;
}