package com.jayjay.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComparisonServiceImplTest {
    private ComparisonService comparisonService;

    @Before
    public void setup(){
        comparisonService = new ComparisonServiceImpl();
    }

    @Test
    public void shouldMatchValidWordsWithNumbers() {
        assertTrue(comparisonService.hasMatch("CALL","2255"));
        assertTrue(comparisonService.hasMatch("BALL","2255"));
        assertTrue(comparisonService.hasMatch("CAK","2255"));
        assertTrue(comparisonService.hasMatch("AB","2255"));
    }

    @Test
    public void shouldNotMatchInvalidWordsWithNumbers() {
        assertFalse(comparisonService.hasMatch("WALL","2255"));
        assertFalse(comparisonService.hasMatch("CALX","2255"));
    }
}
