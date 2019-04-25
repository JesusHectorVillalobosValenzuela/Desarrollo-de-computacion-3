

// Villalobos Valenzuela Jesús Héctor
package sockets_uno;

import java.io.IOException;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import java.util.Scanner;

public class Sockets_uno {

    public static void main(String[] args) {
        Cliente(args, 2398);
        //Servidor(args, 2398);
    }

    public static void Cliente ( String[] args, int puerto){
      try {
          Socket socket = new Socket(args[0], puerto);
          Scanner input = new Scanner(socket.getInputStream());
          System.out.println("Respuesta: " + input.nextLine());
      } catch (IOException e) {
          System.out.println(e);
      }
    }

    public static void Servidor (String [] args, int puerto){
      try   (ServerSocket listener = new ServerSocket (puerto)) {
                System.out.println("El Servidor de fecha esta corriendo");
                while (true) {
                try   (Socket socket = listener.accept()){
                    PrintWriter salida;
                    salida = new PrintWriter(socket.getOutputStream(), true) ;
                    salida.println(new Date ().toString());
                    System.out.println("servidor ");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
