package com.jayjay.service;

import com.jayjay.exception.InvalidPhoneNumberException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GeneratorServiceImplTest {
    private GeneratorService generatorService;
    private ComparisonService comparisonService;

    @Before
    public void setup() {
        comparisonService = new ComparisonServiceImpl();
        generatorService = new GeneratorServiceImpl(comparisonService);
    }

    @Test
    public void shouldReturnNewNumberCorrectly() {
        assertEquals(generatorService.newNumber("012345"), "12345");
        assertEquals(generatorService.newNumber("2837412"), "837412");
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void shouldProperlySkipNumbersWithTwoConsecutiveDigitsNotIncludedInEncoding()
            throws InvalidPhoneNumberException {
        List<String> words = new ArrayList<>();
        generatorService.convertNumbers(words, "01", "01");
    }

    @Test
    public void shouldProperlyConvertWordsWithSomeRemainingNumbers() throws InvalidPhoneNumberException {
        List<String> words = Arrays.asList(new String [] {"CALL"});
        List<String> expectedResult = Arrays.asList(new String [] {"CALL-1"});
        generatorService.convertNumbers(words, "22551", "22551");
        assertThat(generatorService.getConversions(), is(expectedResult));
    }

    @Test
    public void shouldProperlyConvertWordsWithSomeRemainingNumbersMultiple() {
        List<String> words = Arrays.asList(new String [] {"CALL"});
        List<String> numbers = Arrays.asList(new String [] {"22551","225522551","2255122551"});
        List<String> expectedResult = Arrays.asList(new String [] {"CALL-1","CALL-CALL-1","CALL-1-CALL-1"});

        generatorService.generateConversion(words, numbers);
        assertThat(generatorService.getConversions(), is(expectedResult));
    }

    @Test
    public void shouldSkipNumberIfTwoConsecutiveDigitsHaveNoMatch() {
        List<String> words = Arrays.asList(new String [] {"CALL"});
        List<String> numbers = Arrays.asList(new String [] {"225599999"});
        List<String> expectedResult = Arrays.asList(new String [] {});

        generatorService.generateConversion(words, numbers);
        assertThat(generatorService.getConversions(), is(expectedResult));
    }

    
}
