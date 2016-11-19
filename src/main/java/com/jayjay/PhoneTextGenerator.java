package com.jayjay;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import com.jayjay.exception.InvalidPhoneNumberException;
import com.jayjay.service.GeneratorService;
import com.jayjay.service.GeneratorServiceImpl;
import com.jayjay.service.TxtReader;
import com.jayjay.service.TxtReaderImpl;

import java.io.IOException;
import java.util.List;

public class PhoneTextGenerator {
    public static void main(String [] args) throws EmptyFileException, IOException, InvalidFileExtensionException {
        TxtReader txtReader = new TxtReaderImpl();
        GeneratorService generatorService = new GeneratorServiceImpl();

        List<String> words = txtReader.readTxt(args[0]);
        List<String> numbers = txtReader.readTxt(args[1]);

        generatorService.generateConversion(words, numbers);
    }
}
