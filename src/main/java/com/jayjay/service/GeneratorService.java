package com.jayjay.service;

import java.util.List;

public interface GeneratorService {
    public String newNumber(String oldNumber);
    public List<String> newWords(List<String> oldWords, String newNumber);
}
