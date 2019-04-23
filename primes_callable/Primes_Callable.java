
    // Villalobos Valenzuela Jesús Héctor

package primes_callable;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Primes_Callable {

   
    public static void main(String[] args) {
        // TODO code application logic here
        int hilos = 4;
        
        Instant inicio = Instant.now();
        HashSet<Long> hSet = findPrimes(Long.parseLong(args[0]), hilos);
        Instant fin = Instant.now();
        
        System.out.println("0-" + args[0] + " Hilos:"+hilos);
        
        
        System.out.println("Tiempo de procesamiento:" + Duration.between(inicio, fin));
        System.out.println("Numeros primos encontrados:" + hSet.size());
        
        
    }
    
    public static HashSet<Long> findPrimes(long numeros, int hilos){
        HashSet<Long> primes = new HashSet<>();
        ExecutorService service = Executors.newFixedThreadPool(hilos);
        
        List<Future<HashSet>> lista = new ArrayList<>();
        long rango = numeros / (long) hilos;
        long inicio = 0;
        long fin;
        
        for (int i = 0; i < hilos; i++) {
            fin = inicio + rango - 1;
            System.out.println(inicio + " - " + fin);
            primeCall tarea = new primeCall(inicio, fin);
            Future<HashSet> futuro = service.submit(tarea);
            lista.add(futuro);
            inicio = inicio + rango;

        }
        
        for (Future<HashSet> futuro : lista){
            try{
                HashSet set = futuro.get();
                boolean bresult = primes.addAll(set);
                System.out.println(" "+bresult);
                
            }catch (InterruptedException | ExecutionException e){
                System.out.println(e);
            }
        }
        service.shutdown();
        
        return primes;
    }
    
    
    
}
