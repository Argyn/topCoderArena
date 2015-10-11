package com.topcoder.rauanm.exercises.abc;

/**
 * Created by Argyn on 02/10/2015.
 */
public class ABC {

    public String addNChars(String resultString, int t, char charToAdd) {
        for(int i=0; i<t; i++) {
            resultString+=charToAdd;
        }

        return resultString;
    }

    public String reverseString(String stringToReverse) {
        char[] chars = stringToReverse.toCharArray();
        for(int i=0; i<chars.length/2; i++) {
            char leftValue = chars[i];
            chars[i] = chars[chars.length-1-i];
            chars[chars.length-1-i] = leftValue;
        }

        return new String(chars);
    }

    public void swap(char[] chars, int i, int j) {
        char leftVal = chars[i];
        chars[i] = chars[j];
        chars[j] = leftVal;
    }

    public String createString(int N, int K) {
        int t = (int) N / 3;

        String scratchString = new String();


        scratchString = addNChars(scratchString, t, 'A');
        scratchString = addNChars(scratchString, t, 'B');
        scratchString = addNChars(scratchString, t, 'C');

        if(N % 3 == 2) {
            scratchString = "A" + scratchString + "C";
        } else if(N % 3 == 1)
            scratchString = "A" + scratchString;

        int countMax = countMax(scratchString);

        if(countMax<K)
            return new String();
        if(K==0)
            return addNChars(new String(), N, 'A');

        String reversedScratchString = reverseString(scratchString);
        char[] reversedChars = reversedScratchString.toCharArray();
        int count = 0;
        while(count < K) {
            for(int i=0; i<reversedChars.length-1; i++) {
                if(reversedChars[i] > reversedChars[i+1]) {
                    swap(reversedChars, i, i + 1);
                    break;
                }
            }
            count++;
        }

        return new String(reversedChars);

    }

    public int countMax(String stringToTest) {
        char[] chars = stringToTest.toCharArray();

        int numberOfPairsFound = 0;
        for(int i=0; i<chars.length; i++) {
            for(int j=i+1; j<chars.length; j++) {
                if(chars[i]<chars[j])
                    numberOfPairsFound++;
            }
        }

        return numberOfPairsFound;
    }
}
