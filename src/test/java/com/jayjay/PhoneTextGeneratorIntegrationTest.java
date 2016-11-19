package com.jayjay;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import com.jayjay.service.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhoneTextGeneratorIntegrationTest {
    private TxtReader txtReader;
    private ComparisonService comparisonService;
    private GeneratorService generatorService;

    @Before
    public void setup() {
        txtReader = new TxtReaderImpl();
        comparisonService = new ComparisonServiceImpl();
        generatorService = new GeneratorServiceImpl(comparisonService);
    }

    @Test
    public void shouldReturnCorrectResultsGivenDictionaryAndPhoneNumberFiles()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File dictionaryFile = new File(getClass().getClassLoader().getResource("dictionary2.txt").getFile());
        File phoneNumberFile = new File(getClass().getClassLoader().getResource("phoneNumbers1.txt").getFile());

        List<String> words = txtReader.readTxt(dictionaryFile.getAbsolutePath());
        List<String> numbers = txtReader.readTxt(phoneNumberFile.getAbsolutePath());

        generatorService.generateConversion(words, numbers);

        List<String> expectedResults = Arrays.asList(new String [] {"CALL-ME","BALL-ME"});
        assertThat(generatorService.getConversions(), is(expectedResults));
    }

    @Test
    public void shouldReturnCorrectResultsGivenDictionaryAndPhoneNumberFilesWithZeroInFront()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File dictionaryFile = new File(getClass().getClassLoader().getResource("dictionary2.txt").getFile());
        File phoneNumberFile = new File(getClass().getClassLoader().getResource("phoneNumbers2.txt").getFile());

        List<String> words = txtReader.readTxt(dictionaryFile.getAbsolutePath());
        List<String> numbers = txtReader.readTxt(phoneNumberFile.getAbsolutePath());

        generatorService.generateConversion(words, numbers);

        List<String> expectedResults = Arrays.asList(new String [] {"CALL-ME","BALL-ME"});
        assertThat(generatorService.getConversions(), is(expectedResults));
    }

    @Test
    public void shouldReturnCorrectResultsGivenAMoreRobustDictionary()
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        File dictionaryFile = new File(getClass().getClassLoader().getResource("dictionary2.txt").getFile());
        File phoneNumberFile = new File(getClass().getClassLoader().getResource("phoneNumbers3.txt").getFile());

        List<String> words = txtReader.readTxt(dictionaryFile.getAbsolutePath());
        List<String> numbers = txtReader.readTxt(phoneNumberFile.getAbsolutePath());

        generatorService.generateConversion(words, numbers);

        List<String> expectedResults = Arrays.asList(new String [] {"CALL-ME","BALL-ME"});
        assertThat(generatorService.getConversions(), is(expectedResults));
    }
}
