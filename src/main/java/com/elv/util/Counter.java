package com.elv.util;

import java.util.ArrayList;
import java.util.List;

public class Counter {

    private List<Integer> counts = new ArrayList<Integer>();
    private int base = 10;

    public Counter(int base, int initSize) {
        this.base = base;
        for (int i = 0; i < initSize; i++) {
            counts.add(0);
        }
    }

    public void add(int amount) {
        if (amount == 0)
            return;
        addTo(amount, 0);
    }

    private void addTo(int amount, int index) {
        Integer n = getCounts().get(index);
        n = n == null ? 0 : n;
        
        if (n + amount < base) {
            getCounts().set(index, n + amount);
            return;
        }

        int newN = (n + amount) % base;
        int newNN = (n + amount) / base;
        getCounts().set(index, newN);
        addTo(newNN, index + 1);
    }

    public List<Integer> getCounts() {
        return counts;
    }
}
