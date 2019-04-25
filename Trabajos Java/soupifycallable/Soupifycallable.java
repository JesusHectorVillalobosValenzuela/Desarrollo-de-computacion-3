/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soupifycallable;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author villalobos28
 */
public class Soupifycallable {

    
    public static void main(String[] args) {
        int inicio = 1016547817;
        int total = 500;
        int hilos = 10;
        Instant i = Instant.now();
        HashMap<String, ArrayList> MapaLibros = genMapa("book", inicio, total, hilos);
        Instant f = Instant.now();
        System.out.println(Duration.between(i, f));
        DespliegaAutores(MapaLibros);
        
    }
 
    public static HashMap<String, ArrayList> genMapa(String archivo_base, int inicio, int total, int hilos) {
        HashMap<String, ArrayList> mapa = new HashMap<String, ArrayList>();
        try{
            String archivo_libro;
            ArrayList<Future> lista = new ArrayList <>();
            ExecutorService servicio = Executors.newFixedThreadPool(hilos);
            for (int i = inicio; i < total + inicio; i++) {
                archivo_libro = archivo_base + i + ".html";
                
                Callable<LibroWcat> tarea = new genLibro(mapa, archivo_libro);
                Future<LibroWcat> futuro = servicio.submit(tarea);
                lista.add(futuro);
            }
           
            for (Future f : lista) {
                try{
                    LibroWcat libro = (LibroWcat ) f.get();
                    synchronized (Soupifycallable.class){
                       ArrayList<LibroWcat>  arrayLibros = mapa.get(libro.autor);
                        if (arrayLibros != null) {
                            arrayLibros.add(libro);
                            
                        }else{
                            arrayLibros = new ArrayList<>();
                            arrayLibros.add(libro);
                            mapa.put(libro.autor, arrayLibros);
                        }
                    }
                    
                }catch (Exception p){
                    System.out.println(p);
                }
            }
            servicio.shutdown();
            
        }catch(Exception e) {
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
