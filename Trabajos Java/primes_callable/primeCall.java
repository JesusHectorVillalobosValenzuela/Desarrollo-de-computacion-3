/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primes_callable;

import java.util.HashSet;
import java.util.concurrent.Callable;

/**
 *
 * @author villalobos28
 */
public class primeCall implements Callable<HashSet> {

    HashSet<Long> hset = new HashSet<>();
    long inicio = 0;
    long fin;

    primeCall(long inicio, long fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
    
    @Override
    public HashSet<Long> call() {
        boolean prime;
        for (long n = inicio; n < fin; n++) {
            prime = true;
            for (long j = 2; j < n; j++) {
                if (n % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                hset.add(n);
            }

        }
        return hset;
    }
    
    

    
    
    
    
}
