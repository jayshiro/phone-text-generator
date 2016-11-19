package com.jayjay.service;

import com.jayjay.exception.InvalidPhoneNumberException;

import java.util.List;

public interface GeneratorService {
    public String newNumber(String oldNumber);
    public List<String> newWords(List<String> oldWords, String newNumber);
    public void convertNumbers(List<String> words, String newNumber, String originalNumber, String conversion)
            throws InvalidPhoneNumberException;
    public List<String> getConversions();
    public void generateConversion(List<String> words, List<String> numbers);
}
