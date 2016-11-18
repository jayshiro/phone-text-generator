package com.jayjay.service;

import java.util.List;

public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public String newNumber(String oldNumber) {
        return oldNumber.substring(1);
    }

    @Override
    public List<String> newWords(List<String> oldWords, String newNumber) {
        return null;
    }
}
