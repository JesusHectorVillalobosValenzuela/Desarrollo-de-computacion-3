
package downloadurlthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DownloadUrlThreads {

    
    public static void main(String[] args) {
        // TODO code application logic here
        String url_base = "https://www.worldcat.org/oclc/";
        String archivo_base = "book";
        DescargarPaginaEjecutor(1016547817,200, url_base, archivo_base);
    }
    
    public static void DescargarPaginaEjecutor ( int inicio , int total, String link_base, String archivo_base){
        int libro;
        String url;
        String archivo;
        ExecutorService servicio = Executors.newFixedThreadPool(2);
        for (int i = 0; i < total; i++) {
            libro = inicio + i;
            url = link_base + libro;
            archivo = archivo_base + libro + ".html";
            GetWebPage hilo = new GetWebPage (url, archivo);
            
           
           
        }
        servicio.shutdown();
    }
    
}
