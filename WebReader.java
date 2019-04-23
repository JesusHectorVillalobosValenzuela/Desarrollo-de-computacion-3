package webreader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class WebReader {

    public static void main(String[] args) throws MalformedURLException {

        int inicio = 1016547817, descargas = 50;

//        String URL = "http://www.worldcat.org/oclc/";
//        String archivo= "/Users/villalobos28/Desktop/Desarrollo 3/book.html";
//        webreader(URL,archivo);
        DescargaPaginas(inicio, descargas);

    }

    public static void webreader(String line, String archivo) throws MalformedURLException {
        String p;
        try {
            URL url = new URL(line);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            while ((p = reader.readLine()) != null) {
                writer.write(p);

            }
            reader.close();
            writer.close();
            System.out.println("Se descargo correctamente el archivo: " + url.getFile());

        } catch (MalformedURLException mue) {
            System.out.println("URL malformado");
        } catch (IOException ie) {
            System.out.println("Error de i/o");
        }
    }

    public static void DescargaPaginas(int inicio, int descargas) {
        for (int i = inicio; i < inicio+descargas; i++) {
            String pagina = "https://www.worldcat.org/oclc/" + i;
            String archivo = "/Users/villalobos28/Desktop/Desarrollo 3/libro" + i + ".html";
            try {
                webreader(pagina, archivo);
                
            } catch (IOException e) {
                System.out.println(e);

            }
        }
    }

}
