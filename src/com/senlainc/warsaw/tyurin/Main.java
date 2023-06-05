package com.senlainc.warsaw.tyurin;

public class Main {

    public static void main(String[] args) {

        int randomInt = (new java.util.Random()).nextInt(999);
        while (randomInt < 100){
            randomInt = (new java.util.Random()).nextInt(999);
        }
        System.out.println("randomInt = " + randomInt);

        System.out.println("the largest digit = " + getLargestDigit(randomInt));
    }

    public static int getLargestDigit (int num) {
        int maxDigit = 0;
        int digit;

        while (num > 0 && maxDigit < 9) {
            digit = num % 10;
            num = (num - digit) / 10;
            if (maxDigit < digit) {
                maxDigit = digit;
            }
        }

        return maxDigit;
    }
}
