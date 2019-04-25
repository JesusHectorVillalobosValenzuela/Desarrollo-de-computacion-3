/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soupifythreads;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static soupifythreads.SoupifyThreads.DespliegaAutores;

/**
 *
 * @author federicocirettgalan
 */

public class genBook implements Runnable
{
    HashMap<String,ArrayList> mapa;
    String archivo;
    public genBook(HashMap<String,ArrayList> mapa_con, String archivo_base){
        mapa = mapa_con;
        archivo = archivo_base;
    }
    @Override
    public void run() {
        
            LibroWcat libro = null;
            File input = new File(archivo);
        
            Document doc;
            try {
                String a ;
                String e ;
                String i ;
                String o ;
                String n ;
                String d ;
                doc = Jsoup.parse(input,"UTF-8");
                
                //titulo
                Elements titulo = doc.getElementsByClass("title");
                String t = titulo.text();
                
               //autor
               try {
                Elements autor = doc.getElementsByAttributeValue​("title","Search for more by this author");
                //System.out.println(autor.get(0).text()+"\n---------");
                
                    a =  autor.get(0).text();
                } catch (Exception exe) {
                    
                    a = " ";
                }
                
                //editor
                try {
                    Elements editorial = doc.getElementsByAttributeValue("id","bib-publisher-cell");
                    //System.out.println(editorial.text()+"\n---------");
                    e = editorial.text();
                } catch (Exception exep) {
                    e = " ";
                }
                
                //isbn
                try {
                    Element isbn = doc.getElementById("details-standardno");
                    Elements ISBN = isbn.select("td");
                    //System.out.println(ISBN.text()+"\n---------");
                    i = ISBN.text();
                } catch (Exception ei) {
                    i = " ";
                }
                
                //oclc
                try {
                    Element oclc = doc.getElementById("details-oclcno");
                    Elements OCLC = oclc.select("td");
                    //System.out.println(OCLC.text()+"\n---------");
                    o = OCLC.text();
                } catch (Exception eo) {
                    o = " ";
                }
                
                //Notes
                try {
                    Element notes = doc.getElementById("details-notes");
                    Elements NOTES = notes.select("td");
                    //System.out.println(NOTES.text()+"\n---------");
                    n = NOTES.text();
                } catch (Exception en) {
                    n = " ";
                }
                
                //description
                try {
                    Element description = doc.getElementById("details-description");
                    Elements DESC = description.select("td");
                    //System.out.println(DESC.text()+"\n---------");
                    d = DESC.text();
                } catch (Exception ed) {
                    d = " ";
                }
                libro = new LibroWcat(t,a ,e,i,o,n,d);
                //System.out.println(libro.titulo);
                //synchronized (this) 
                {
                    ArrayList<LibroWcat> ArrayLibros = mapa.get(libro.autor);
                    if (ArrayLibros != null){
                        ArrayLibros.add(libro);
                    } else {
                        ArrayLibros = new ArrayList<>();
                        ArrayLibros.add( libro);
                        mapa.put(libro.autor, ArrayLibros);
                    }
                }
    
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
     DespliegaAutores(mapa);
    }
}
