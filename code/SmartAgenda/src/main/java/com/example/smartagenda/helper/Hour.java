package com.example.smartagenda.helper;

import java.util.Comparator;

public class Hour implements Comparator<Hour> {
    private int hour;
    private int minutes;

    @Override
    public int compare(Hour h1, Hour h2) {
        if (h1.hour < h2.hour)
            return 1;
        if (h1.hour == h2.hour && h1.minutes < h2.minutes)
            return 1;
        return 0;
    }
}
