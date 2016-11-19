package com.jayjay.service;

import com.jayjay.exception.InvalidPhoneNumberException;
import com.jayjay.model.NumberEncoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneratorServiceImpl implements GeneratorService {
    private int skippedNumbers;
    private String conversion = "";
    private List<String> conversions = new ArrayList<>();

    private ComparisonService comparisonService;

    public GeneratorServiceImpl(ComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    @Override
    public String newNumber(String oldNumber) {
        return oldNumber.substring(1);
    }

    @Override
    public void convertNumbers(List<String> words, String number, String originalNumber, String conversion)
            throws InvalidPhoneNumberException {
        boolean hasMatch = false;

        int firstDigit = Integer.parseInt(number.substring(0,1));
        Optional<NumberEncoding> numberEncoding = NumberEncoding.findByDigit(firstDigit);

        if(!numberEncoding.isPresent()) {
            ++skippedNumbers;

            if(skippedNumbers >= 2) {
                throw new InvalidPhoneNumberException(originalNumber + " has been skipped.");
            }

            handleSingleDigitNumber(words, number, originalNumber, conversion, firstDigit);
            return;
        }

        for(String word : words) {
            word = comparisonService.capitalize(word);

            if(comparisonService.hasMatch(word, number)) {
                skippedNumbers = 0;

                if(word.length() == number.length()) {
                    conversions.add(conversion + word);
                } else {
                    convertNumbers(words, number.substring(word.length(), number.length()), originalNumber,
                            conversion + word + "-");
                }


                hasMatch = true;
            }
        }

        if(!hasMatch) {
            ++skippedNumbers;
            if(skippedNumbers >= 2) {
                throw new InvalidPhoneNumberException(originalNumber + " has been skipped.");
            }

            handleSingleDigitNumber(words, number, originalNumber, conversion, firstDigit);
        }

    }

    @Override
    public List<String> getConversions() {
        return conversions;
    }

    @Override
    public void generateConversion(List<String> words, List<String> numbers) {
        conversions = new ArrayList<>();

        for(String number : numbers) {
            skippedNumbers = 0;
            conversion = "";
            try {
                convertNumbers(words, number, number, "");
            } catch (InvalidPhoneNumberException ipne) {
            }
        }

        for(String conversion : conversions) {
            System.out.println(conversion);
        }

    }

    private void handleSingleDigitNumber(List<String> words, String number, String originalNumber, String conversion,
                                         int firstDigit) throws InvalidPhoneNumberException {
        if(number.length() - 1 == 0) {
            if(number != originalNumber) {
                conversions.add(conversion + number);
            }
        } else {
            number = newNumber(number);
            convertNumbers(words, number, originalNumber, conversion + firstDigit + "-");
        }

    }
}
