/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numeros_primos;

import java.util.HashSet;

/**
 *
 * @author villalobos28
 */
public class primeThread implements Runnable{

    HashSet<Long> hset = new HashSet<>();
    long inicio = 0;
    long fin;

    primeThread(HashSet<Long> hset, long inicio, long fin) {
        this.hset = hset;
        this.inicio = inicio;
        this.fin = fin;
    }

    
    @Override
    public void run() {
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
    }
    
}
