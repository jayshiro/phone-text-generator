package com.jayjay.service;

import com.jayjay.exception.InvalidFileExtensionException;
import org.junit.Before;
import org.junit.Test;

public class TxtReaderImplTest {
    private TxtReader txtReader;

    @Before
    public void setup(){
        txtReader = new TxtReaderImpl();
    }

    @Test(expected = InvalidFileExtensionException.class)
    public void shouldThrowExceptionWhenFileExtensionIsInvalid() throws InvalidFileExtensionException {
        txtReader.readTxt("helloWold.txd");
    }

    @Test
    public void shouldNowThrowExceptionWhenFileExtensionIsValid() throws InvalidFileExtensionException {
        txtReader.readTxt("helloUniverse.txt");
    }
}
