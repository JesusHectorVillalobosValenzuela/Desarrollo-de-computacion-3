package soupifyhtml;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.jsoup.nodes.Document;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SoupifyHTML {

    public static void main(String[] args) throws InterruptedException {
        //String archivo = "libro1016547817.html";
        int inicio = 1016547817;
        int total = 500;
        //String Archivo_base = "libro"+inicio+".html";
        //LibroWcat libro = generaLibro(Archivo_base);
        //Imprimir(libro);
        //HashMap<String, LibroWcat> map_libros = new HashMap<>();
        //map_libros = GeneraMapa("libro", inicio, total);
        //DespliegaMapa(map_libros);
        
        Instant Inicio = Instant.now();
        HashMap<String, ArrayList> MapaLibros = GeneraLista("book", inicio, total);
        Instant fin = Instant.now();
        DespliegaAutores(MapaLibros);
        System.out.println(Duration.between(Inicio, fin));


    }

    public static LibroWcat generaLibro(String nombre_archivo) {

        LibroWcat ejemplar = null;
        String t = "", a = "", e = "", n = "", d = "", o = "", i = "";
        try {
            File input = new File(nombre_archivo);
            Document doc = null;

            try {
                //Titulo **
                doc = Jsoup.parse(input, "UTF-8");
                Elements titulo = doc.getElementsByClass("title");
                t = titulo.get(0).text();
                //   System.out.println("Titulo: " + t);

            } catch (Exception ex) {
                t = " ";
            }
            try {
                //Autor **
                doc = Jsoup.parse(input, "UTF-8");
                Elements autor = doc.getElementsByAttributeValue("title", "Search for more by this author");
                a = autor.get(0).text();
                //  System.out.println("Autor: " + a);

            } catch (Exception ex) {
                a = " ";
            }
            try {
                //Editorial **
                doc = Jsoup.parse(input, "UTF-8");
                Elements editorial = doc.getElementsByAttributeValue("id", "bib-publisher-cell");
                e = editorial.get(0).text();
                //  System.out.println("Editorial: " + e);
            } catch (Exception ex) {
                e = " ";
            }
            try {
                //ISBN **
                doc = Jsoup.parse(input, "UTF-8");
                Element isbn = doc.getElementById("details-standardno");
                i = isbn.text();
                // System.out.println("ISBN: " + i);
            } catch (Exception ex) {
                i = " ";
            }
            try {
                //OCLC **
                doc = Jsoup.parse(input, "UTF-8");
                Element oclc = doc.getElementById("details-oclcno");
                o = oclc.text();
                //  System.out.println("OCLC: " + o);
            } catch (Exception ex) {
                o = " ";
            }
            try {
                //Notas **
                doc = Jsoup.parse(input, "UTF-8");
                Element notas = doc.getElementById("details-notes");
                n = notas.text();
                System.out.println("Notas: " + n);
            } catch (Exception ex) {
                n = " ";
            }
            try {
                //Descripcion **
                doc = Jsoup.parse(input, "UTF-8");
                Element desc = doc.getElementById("details-description");
                d = desc.text();
                System.out.println("Descripcion: " + d);
            } catch (Exception ex) {
                d = " ";
            }
            ejemplar = new LibroWcat(t, a, e, i, o, n, d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ejemplar;
    }

    private static void Imprimir(LibroWcat libro) {
        System.out.println(libro.titulo);
        System.out.println(libro.autor);
        System.out.println(libro.editorial);
        System.out.println(libro.isbn);
        System.out.println(libro.oclc);
        System.out.println(libro.notas);
        System.out.println(libro.descripcion);
    }

    public static HashMap<String, LibroWcat> GeneraMapa(String archivo_base, int inicio, int total) throws InterruptedException {
        HashMap<String, LibroWcat> map_libro = new HashMap<>();

        for (int i = inicio; i < inicio + total; i++) {
            String archivo = archivo_base + i + ".html";
            LibroWcat libro = generaLibro(archivo);
            LibroWcat put = map_libro.put(libro.autor, libro);

            DespliegaMapa(map_libro);

        }
        return map_libro;
    }

    public static void DespliegaMapa(HashMap<String, LibroWcat> mapa) {
        //map.forEach((k, v) -> System.out.println(k + v));
        // Obtenemos todas las llaves del mapa.
        Set<String> mapKeys = mapa.keySet();

        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : mapKeys) {
            // Obtenemos el value.
            String value = mapa.get(key).toString();
            // Imprimimos.
            System.out.printf("Key: [ %s ], Value: [ %s ].\n", key, value);
        }
    }

    public static void DespliegaMapa_Array(HashMap<String, ArrayList> mapa) {
        Set<String> mapKeys = mapa.keySet();

        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : mapKeys) {
            // Obtenemos el value.
            String value = mapa.get(key).toString();
            // Imprimimos.
            System.out.printf("Key: [ %s ], Value: [ %s ].\n", key, value);
        }
    }

    public static HashMap<String, ArrayList> GeneraLista(String base, int inicio, int total) {
        HashMap<String, ArrayList> mapa = new HashMap<>();
        try {
            String archivo_libro;
            for (int i = inicio; i < inicio + total; i++) {
                archivo_libro = base + i + ".html";
                LibroWcat libro = generaLibro(archivo_libro);
                ArrayList<String> ArrayLibros = mapa.get(libro.autor);
                if (ArrayLibros != null) {
                    ArrayLibros.add(libro.toString());
                } else {
                    ArrayLibros = new ArrayList<String>();
                    ArrayLibros.add(libro.titulo);
                    mapa.put(libro.autor, ArrayLibros);
                }
            }
        } catch (Exception w) {
            System.out.println(w);
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

    public static void DespliegaMapaC(HashMap<String, ArrayList> map) {
        for (HashMap.Entry<String, ArrayList> valor : map.entrySet()) {
            ArrayList<LibroWcat> lista_libros = valor.getValue();
            for (LibroWcat libro : lista_libros) {
                System.out.println("Titulo:" + libro.titulo);
                System.out.println("Autor:" + libro.autor);
                System.out.println("----------------------");
            }
        }
    }

}
