package com.jayjay.service;

import com.jayjay.exception.InvalidPhoneNumberException;
import com.jayjay.model.NumberEncoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GeneratorServiceImpl implements GeneratorService {
    private int skippedNumbers;
    private String conversion = "";
    private List<String> conversions = new ArrayList<>();

    private ComparisonService comparisonService;

    public GeneratorServiceImpl(){}

    public GeneratorServiceImpl(ComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    @Override
    public String newNumber(String oldNumber) {
        return oldNumber.substring(1);
    }

    @Override
    public List<String> newWords(List<String> oldWords, String newNumber) {
        return oldWords.stream()
                .filter(oldWord -> {
                    int firstDigit = Integer.parseInt(newNumber.substring(0,1));
                    int length = newNumber.length();
                    NumberEncoding numberEncoding = NumberEncoding.findByDigit(firstDigit).get();

                    if(oldWord.length() <= length && numberEncoding.contains(oldWord.substring(0,1))) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void convertNumbers(List<String> words, String number, String originalNumber)
            throws InvalidPhoneNumberException {
        boolean hasMatch = false;

        int firstDigit = Integer.parseInt(number.substring(0,1));
        Optional<NumberEncoding> numberEncoding = NumberEncoding.findByDigit(firstDigit);

        if(!numberEncoding.isPresent()) {
            ++skippedNumbers;

            if(skippedNumbers >= 2) {
                throw new InvalidPhoneNumberException(originalNumber + " has been skipped.");
            }

            if(number.length() - 1 == 0) {
                conversion += number;
                conversions.add(conversion);
                conversion = "";
            } else {
                conversion += firstDigit + "-";
                number = newNumber(number);
                convertNumbers(words, number, originalNumber);
            }

            return;
        }

        for(String word : words) {
            if(comparisonService.hasMatch(word, number)) {
                skippedNumbers = 0;

                if(word.length() == number.length()) {
                    conversion += word;
                    conversions.add(conversion);
                    conversion = "";
                } else {
                    conversion += word + "-";
                    convertNumbers(words, number.substring(word.length(), number.length()), originalNumber);
                }


                hasMatch = true;
            }
        }

        if(!hasMatch) {
            ++skippedNumbers;
            if(skippedNumbers >= 2) {
                throw new InvalidPhoneNumberException(originalNumber + " has been skipped.");
            }

            if(number.length() - 1 == 0) {
                conversion += number;
                conversions.add(conversion);
                conversion = "";
            } else {
                conversion += firstDigit + "-";
                number = newNumber(number);
                convertNumbers(words, number, originalNumber);
            }
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
            try {
                convertNumbers(words, number, number);
            } catch (InvalidPhoneNumberException ipne) {
                System.out.println(ipne.getMessage());
            }
        }

        for(String conversion : conversions) {
            System.out.println(conversion);
        }

    }
}
