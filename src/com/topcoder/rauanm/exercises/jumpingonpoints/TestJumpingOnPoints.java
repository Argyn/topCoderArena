package com.topcoder.rauanm.exercises.jumpingonpoints;

/**
 * Created by rauanm on 06/10/2015.
 */
public class TestJumpingOnPoints {

    public static void main(String[] args) {
        JumpingOnPoints jmp = new JumpingOnPoints();
        System.out.println("Result = "+jmp.sumOfDistances(60000, 0, new int[]{0, 1, 1, 1000000000, 0, 1, 1, 1000000000, 1, 1, 0, 1000000000, 999999999, 1, 0, 1000000000}));
    }
}