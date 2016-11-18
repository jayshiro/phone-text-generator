package com.jayjay.service;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TxtReaderImplTest {
    private TxtReader txtReader;

    @Before
    public void setup(){
        txtReader = new TxtReaderImpl();
    }

    @Test(expected = InvalidFileExtensionException.class)
    public void shouldThrowExceptionWhenFileExtensionIsInvalid()
            throws InvalidFileExtensionException, IOException, EmptyFileException {
        txtReader.readTxt("helloWold.txd");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenFileDoesNotExist()
            throws InvalidFileExtensionException, IOException, EmptyFileException {
        txtReader.readTxt("yeah.txt");
    }

    @Test
    public void shouldCorrectlyAccessAValidFile()
            throws InvalidFileExtensionException, IOException, EmptyFileException {
        File file = new File(getClass().getClassLoader().getResource("phoneNumbers.txt").getFile());
        txtReader.readTxt(file.getAbsolutePath());
    }

    @Test
    public void shouldRemoveNonAlphabetNonNumericCharacters() {
        assertEquals(txtReader.removeUnwantedCharacters("123.325"),"123325");
        assertEquals(txtReader.removeUnwantedCharacters("2255;;;34"),"225534");
        assertEquals(txtReader.removeUnwantedCharacters("CALL ER $s"), "CALLERs");
    }

    @Test(expected = EmptyFileException.class)
    public void shouldThrowExceptionWhenFileDoesNotHaveValidContent()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File file = new File(getClass().getClassLoader().getResource("dictionary1.txt").getFile());
        txtReader.readTxt(file.getAbsolutePath());
    }
}
