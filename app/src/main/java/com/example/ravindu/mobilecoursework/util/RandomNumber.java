package com.example.ravindu.mobilecoursework.util;

/**
 * Created by Ravindu Fernando on 2020-03-04 at 11:18 PM
 */
public class RandomNumber {
    public static int generateRandomNumber(int min, int max) {
        double value = (Math.random() * ((max - min) + 1)) + min;
        return (int) value;
    }
}
