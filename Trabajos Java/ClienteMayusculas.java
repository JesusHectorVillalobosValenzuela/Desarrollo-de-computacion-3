
  // Villalobos Valenzuela Jesús Héctor

package clientemayusculas;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMayusculas {


    public static void main(String[] args) {
        // TODO code application logic here
        String ip ="10.10.142.217";
        try {
          Socket socket = new Socket(ip,9090);
          System.out.println("Escribe algo");
          Scanner scanner = new Scanner(System.in);
          Scanner entrada = new Scanner(socket.getInputStream());
          PrintWriter salida = new PrintWriter( socket.getOutputStream(), true);
          while (scanner.hasNextLine()) {
              
            salida.println(scanner.nextLine());
            System.out.println(entrada.nextLine());
            
          }

        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
