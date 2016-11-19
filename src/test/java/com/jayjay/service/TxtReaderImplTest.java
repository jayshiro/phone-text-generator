package com.jayjay.service;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

    @Test
    public void shouldGenerateTheCorrectListOfWords()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File file = new File(getClass().getClassLoader().getResource("dictionary.txt").getFile());
        List<String> words = txtReader.readTxt(file.getAbsolutePath());
        List<String> expectedWords = Arrays.asList(new String [] {"call","ball","hall"});
        assertThat(words, is(expectedWords));
    }

    @Test
    public void shouldGenerateTheCorrectListOfNumbers()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File file = new File(getClass().getClassLoader().getResource("phoneNumbers.txt").getFile());
        List<String> numbers = txtReader.readTxt(file.getAbsolutePath());
        List<String> expectedNumbers = Arrays.asList(new String [] {"225563","48234","923828423"});
        assertThat(numbers, is(expectedNumbers));
    }

    @Test
    public void shouldRemoveNonNumericCharacters() {
        assertEquals(txtReader.extractNumbers("123.325"),"123325");
        assertEquals(txtReader.extractNumbers("2255;;;34"),"225534");
        assertEquals(txtReader.extractNumbers("CALL ER $s2"), "2");
    }
}
