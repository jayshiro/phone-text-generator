package com.jayjay.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumberEncodingTest {

    @Test
    public void shouldReturnCorrectNumberEncodingBasedOnDigit() {
        assertEquals(NumberEncoding.findByDigit(2).get().getDigit(), 2);
        assertEquals(NumberEncoding.findByDigit(4).get().getDigit(), 4);
    }

    @Test
    public void shouldReturnTrueIfEnumContainsStringCharacter() {
        assertTrue(NumberEncoding.EIGHT.contains("U"));
        assertTrue(NumberEncoding.NINE.contains("X"));
    }
}
