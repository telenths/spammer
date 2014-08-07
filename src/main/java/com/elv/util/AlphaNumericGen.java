package com.elv.util;

import java.util.ArrayList;
import java.util.List;

public class AlphaNumericGen {

    private static List<Character> libList = new ArrayList<Character>();

    private boolean alphabet = false;
    private boolean numbers = false;
    private boolean underscore = false;
    
    private int step = 1;

    private void init() {
        if (alphabet)
            for (int i = 0; i < 26; i++)
                libList.add(Character.toChars(97 + i)[0]);
        if (numbers)
            for (int i = 0; i < 10; i++)
                libList.add(Character.toChars(48 + i)[0]);
        if (underscore)
            libList.add('_');
    }

    public List<String> gen(int stringLenght, int index, int count) {

        init();

        List<String> result = new ArrayList<String>();

        Counter counter = new Counter(libList.size(), stringLenght);
        counter.add(index);
        while (result.size() < count) {
            String str = getString(counter.getCounts());
            StringBuilder builder = new StringBuilder(str);
            result.add(builder.reverse().toString());
            if(step > 0)
                counter.add(step);
            else{
                int random = (int) (Math.random() * Math.abs(step));
                counter.add(random);
            }
        }

        return result;
    }

    private String getString(List<Integer> counts) {
        StringBuilder builder = new StringBuilder();
        for (Integer i : counts) {
            builder.append(libList.get(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        AlphaNumericGen a = new AlphaNumericGen();
        a.setNumbers(true);
        a.setAlphabet(true);
        a.setUnderscore(true);
        List<String> result = a.gen(6, 0, 100);
        System.out.println(result);

    }

    public void setAlphabet(boolean alphabet) {
        this.alphabet = alphabet;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public void setUnderscore(boolean underscore) {
        this.underscore = underscore;
    }

    public void setStep(int step) {
        this.step = step;
    }

}
