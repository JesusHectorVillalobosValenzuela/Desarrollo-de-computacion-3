
package downloadurlthreads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetWebPage implements Runnable {
    String webpage;
    String archivo_salida;

    public GetWebPage(String webpage, String archivo_salida) {
        webpage = webpage;
        archivo_salida = archivo_salida;
    }
    

    @Override
    public void run() {
        try {
            URL url = new URL(webpage);
            BufferedWriter writer;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                writer = new BufferedWriter(new FileWriter(archivo_salida));
                String line;
                while ((line = reader.readLine())!=null) {
                    writer.write(line);
                } writer.close();
                System.out.println("Archivo: " + archivo_salida + " descargado");
            } catch(MalformedURLException m){
                System.out.println("URL Malformada");
            }
        } catch(IOException ie ) {
            System.out.println("Error de l/O");
        }
    }
    
}
