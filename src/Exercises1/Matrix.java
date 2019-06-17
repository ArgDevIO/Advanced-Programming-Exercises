package Exercises1;

import java.util.Arrays;

/**
 * TODO
 * Implement methods:
 * sum(double[][] a):double
 * average(double[][] a):double
 * Which as parameters take two dimensional arrays(matrix) of 'double'
 * As a result they need to return the sum and average of those matrices
 */

public class Matrix {

    public static void main(String[] args) {
        double[][] a = {
                {2.5, 2, 5.2, 1.1},
                {6.1, 5.2, 0.1, 1.2},
                {5.5, 2.2, 0, 8.5}
        };

        //Testing methods
        System.out.println("Sum:\t" + sum(a));
        System.out.println("SumStream:\t" + sumStream(a));
        System.out.println("Average:\t" + average(a));
    }

    //Without Streams
    public static double sum(double[][] a) {
        double sum = 0;
        for (double[] rows : a) {
            for (double aDouble : rows) {
                sum += aDouble;
            }
        }
        return sum;
    }

    //Using Streams
    public static double sumStream(double[][] a) {
        return Arrays.stream(a)
                .mapToDouble(row -> Arrays.stream(row).sum())
                .sum();
    }

    public static double average(double[][] a) {
        double sum = 0;
        for (double[] rows : a) {
            for (double aDouble : rows) {
                sum += aDouble;
            }
        }
        return sum / (a.length * a[0].length);
    }
}