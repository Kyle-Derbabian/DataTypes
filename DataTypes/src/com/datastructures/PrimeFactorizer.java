//package main.rice;
package com.datastructures;

/**
 * This class implements a relatively simple algorithm for computing (and printing) the
 * prime factors of a number.  At initialization, a list of primes is computed. Given a
 * number, this list is used to efficiently compute the prime factors of that number.
 */
public class PrimeFactorizer {

    private final int maxNumToFactorize;
    private final int maxPrime;
    private final int[] primes;

    public PrimeFactorizer(int maxNumToFactorize) {
        this.maxNumToFactorize = maxNumToFactorize;
        this.maxPrime = (int) Math.ceil(Math.sqrt(this.maxNumToFactorize));
        this.primes = generatePrimes();
    }

    public int[] computePrimeFactorization(int numToFactorize) {

        if (numToFactorize < 2 || numToFactorize > this.maxNumToFactorize) {
            return null;
        } else if (this.isPrime(numToFactorize)) {
            return new int[] {numToFactorize};
        }

        int[] primeFactors = new int[0];
        while (numToFactorize > 1 && !this.isPrime(numToFactorize)) {
            for (int prime : this.primes) {
                if (numToFactorize % prime == 0) {
                    primeFactors = this.appendToIntArray(primeFactors, prime);
                    numToFactorize /= prime;
                    break;
                }
            }
        }
        primeFactors = this.appendToIntArray(primeFactors, numToFactorize);

        return primeFactors;

    }


    private int[] generatePrimeCandidates() {
        int[] primeCandidates = new int[this.maxPrime - 1];
        for (int i = 2; i <= this.maxPrime; i++) {
            primeCandidates[i - 2] = i;
        }

        return primeCandidates;
    }

    private int[] generatePrimes() {

        // Generates prime candidates array and initializes prime booleans array
        int[] primeCandidates = generatePrimeCandidates();
        boolean[] primeBooleans = new boolean[primeCandidates.length];
        for (int i = 0; i < primeBooleans.length; i++) {
            primeBooleans[i] = true;
        }

        // Crosses out composites and counts the number of composites
        int compositeCount = 0;
        for (int i = 0; i < primeCandidates.length; i++) {

            // If it's false, continue
            if (!primeBooleans[i]) {
                compositeCount++;
                continue;
            }

            // Crosses out multiples
            int currentCandidate = primeCandidates[i];
            for (int j = i + currentCandidate; j < primeBooleans.length; j += currentCandidate) {
                primeBooleans[j] = false;
            }

        }

        // Generates the primes array according to the boolean array
        int[] primes = new int[primeCandidates.length - compositeCount];
        int index = 0;
        for (int i = 0; i < primeCandidates.length; i++) {
            if (primeBooleans[i]) {
                primes[index] = primeCandidates[i];
                index++;
            }
        }

        return primes;

    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int[] appendToIntArray(int[] array, int x) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = x;
        return newArray;
    }



}
