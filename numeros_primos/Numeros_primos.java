package numeros_primos;

// Villalobos Valenzuela Jesús Héc

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Numeros_primos {

    public static void main(String[] args) {

        int hilos = 4;
        
        Instant inicio = Instant.now();
        HashSet<Long> hSet = PrimesThread(Long.parseLong(args[0]), hilos);
        //HashSet<Long> hSet = findPrimes(Long.parseLong(args[0]));
        Instant fin = Instant.now();
        
        System.out.println("0-" + args[0] + " Hilos:"+hilos);
        //System.out.println("0-" + args[0] + " Hilos: 1");
        
        System.out.println("Tiempo de procesamiento:" + Duration.between(inicio, fin));
        System.out.println("Numeros primos encontrados:" + hSet.size());
        //System.out.println(hSet);
        
        
    }

    public static HashSet<Long> findPrimes(long numero) {
        HashSet<Long> primeSet = new HashSet<>();
        boolean prime;
        for (long n = 2; n < numero; n++) {
            prime = true;
            for (long j = 2; j < n; j++) {
                if (n % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primeSet.add(n);
            }

        }
        return primeSet;
    }

    public static HashSet<Long> PrimesThread(long numero_final, int hilos) {
        HashSet<Long> primeSet = new HashSet<>();

        ExecutorService service = Executors.newFixedThreadPool(hilos);

        long rango = numero_final / (long) hilos;
        long inicio = 0;
        long fin;

        for (int i = 0; i < hilos; i++) {
            fin = inicio + rango - 1;
            System.out.println(inicio + " - " + fin);
            primeThread pt = new primeThread(primeSet, inicio, fin);
            service.execute(pt);
            inicio = inicio + rango;

        }

        try {
            if (service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {

            } else{
                service.shutdownNow();
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }

        return primeSet;
    }

    

}
