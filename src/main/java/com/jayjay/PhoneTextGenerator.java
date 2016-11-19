package com.jayjay;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import com.jayjay.exception.InvalidPhoneNumberException;
import com.jayjay.service.*;

import java.io.IOException;
import java.util.List;

public class PhoneTextGenerator {
    public static void main(String [] args) throws EmptyFileException, IOException, InvalidFileExtensionException {
        String str = "ab";
        System.out.println(str.substring(1,1));

        TxtReader txtReader = new TxtReaderImpl();
        ComparisonService comparisonService = new ComparisonServiceImpl();
        GeneratorService generatorService = new GeneratorServiceImpl(comparisonService);

        List<String> words = txtReader.readTxt(args[0]);
        List<String> numbers = txtReader.readTxt(args[1]);

        generatorService.generateConversion(words, numbers);
    }
}
