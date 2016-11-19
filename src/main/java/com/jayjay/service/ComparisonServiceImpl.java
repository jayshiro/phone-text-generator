package com.jayjay.service;

import com.jayjay.model.NumberEncoding;

import java.util.Optional;

public class ComparisonServiceImpl implements ComparisonService {
    @Override
    public boolean hasMatch(String word, String number) {
        String str = "";
        String num = "";

        if(word.length() > number.length()) {
            return false;
        }

        for(int i = 0; i < word.length(); i++) {
            str = word.substring(i,i+1);
            num = number.substring(i,i+1);

            int digit = Integer.parseInt(num);
            Optional<NumberEncoding> opt = NumberEncoding.findByDigit(digit);

            if(!opt.isPresent()) {
                return false;
            }

            NumberEncoding numberEncoding = opt.get();
            if(!numberEncoding.contains(str)) {
                return false;
            }
        }
        return true;
    }
}
