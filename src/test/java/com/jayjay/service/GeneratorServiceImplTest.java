package com.jayjay.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratorServiceImplTest {
    private GeneratorService generatorService;

    @Before
    public void setup() {
        generatorService = new GeneratorServiceImpl();
    }

    @Test
    public void shouldReturnNewNumberCorrectly() {
        assertEquals(generatorService.newNumber("012345"), "12345");
        assertEquals(generatorService.newNumber("2837412"), "837412");
    }

}
