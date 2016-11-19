package com.jayjay;

import com.jayjay.exception.EmptyFileException;
import com.jayjay.exception.InvalidFileExtensionException;
import com.jayjay.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PhoneTextGenerator {
    public static void main(String [] args) throws EmptyFileException, IOException, InvalidFileExtensionException {
        String dictionaryPath = "dictionary.txt";
        String phoneNumbersPath = "";

        if(args.length > 0) {
            if(args[0].equals("-d")) {
                dictionaryPath = args[1];

                if(args.length > 2) {
                    phoneNumbersPath = args[2];
                }
            } else {
                if(args[0] != null) {
                    phoneNumbersPath = args[0];
                }
            }
        }

        execute(dictionaryPath, phoneNumbersPath);
    }

    private static void execute(String dictionaryPath, String phoneNumbersPath)
            throws EmptyFileException, IOException, InvalidFileExtensionException {
        TxtReader txtReader = new TxtReaderImpl();
        ComparisonService comparisonService = new ComparisonServiceImpl();
        GeneratorService generatorService = new GeneratorServiceImpl(comparisonService);

        List<String> words = txtReader.readTxt(dictionaryPath);

        if(phoneNumbersPath.length() > 0) {
            List<String> numbers = txtReader.readTxt(phoneNumbersPath);
            generatorService.generateConversion(words, numbers);
        } else {
            Scanner scanner = new Scanner(System.in);
            String input = "";
            String phoneNumber = "";

            while(!input.equals("quit")) {
                input = scanner.nextLine();
                phoneNumber = txtReader.extractNumbers(input);

                if(phoneNumber.length() > 0) {
                    generatorService.generateConversion(words, txtReader.extractNumbers(phoneNumber));
                }
                System.out.println("###############");
            }

            System.out.println("Program has been terminated. Thank you for using Phone Text Generator.");
        }

    }
}
