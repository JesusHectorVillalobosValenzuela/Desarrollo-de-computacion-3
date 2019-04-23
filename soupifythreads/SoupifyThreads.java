package soupifythreads;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SoupifyThreads {

    public static void main(String[] args) throws InterruptedException {

        int inicio = 1016547817;
        int total = 500;
        Instant i = Instant.now();
        HashMap<String, ArrayList> MapaLibros = GeneraMapa("book", inicio, total);
        Instant f = Instant.now();
        System.out.println(Duration.between(i, f));
        DespliegaAutores(MapaLibros);
        
        
    }
    
    public static HashMap<String, ArrayList> GeneraMapa(String base, int inicio, int total) throws InterruptedException {
        HashMap<String, ArrayList> mapa = new HashMap<>();

        try{
            ExecutorService service = Executors.newFixedThreadPool(6);
            String archivo_libro;
            for (int i = inicio; i < inicio+total; i++) {
                archivo_libro = base+i+".html";
                //System.out.println(archivo_libro);
                genBook t = new genBook (mapa,archivo_libro);
                service.execute(t);
            }
            if (!(service.awaitTermination(3000, TimeUnit.MILLISECONDS))) {
                service.shutdown();
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return mapa;
    }
    
    
    public static void DespliegaAutores(HashMap<String, ArrayList> map) {
        int total = 0;
        int num_ejemplares;
        
        for (HashMap.Entry<String, ArrayList> entrada : map.entrySet()) {
            num_ejemplares = entrada.getValue().size();
            System.out.println(entrada.getKey() + ":" + num_ejemplares);
            total = total + num_ejemplares;
        }
        
        System.out.println("------------------------");
        System.out.println("total ejemplares:" + total);
        System.out.println("total autores :" + map.size());
        System.out.println("-----");
    }

    

}
