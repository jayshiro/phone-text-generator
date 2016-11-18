package com.jayjay.service;

import com.jayjay.exception.InvalidFileExtensionException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TxtReaderImplTest {
    private TxtReader txtReader;

    @Before
    public void setup(){
        txtReader = new TxtReaderImpl();
    }

    @Test(expected = InvalidFileExtensionException.class)
    public void shouldThrowExceptionWhenFileExtensionIsInvalid() throws InvalidFileExtensionException, IOException {
        txtReader.readTxt("helloWold.txd");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenFileDoesNotExist() throws InvalidFileExtensionException, IOException {
        txtReader.readTxt("yeah.txt");
    }

    @Test
    public void shouldCorrectlyAccessAValidFile() throws InvalidFileExtensionException, IOException {
        File file = new File(getClass().getClassLoader().getResource("phoneNumbers.txt").getFile());
        txtReader.readTxt(file.getAbsolutePath());
    }
}
