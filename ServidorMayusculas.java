// Villalobos Valenzuela Jesús Héctor
package servidormayusculas;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorMayusculas {

    private static class Mayusculas implements Runnable {

        private Socket socket;

        public Mayusculas(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                Scanner entrada = new Scanner(socket.getInputStream());
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Servidor");
                System.out.println("Conectado: " + socket);

                //System.out.println(">"+entrada.next());
                while (entrada.hasNext()) {

                    String cadena = entrada.nextLine();
                    String[] partes = cadena.split(" ");

                    if (partes[0].equals("list")) {
                        cadena = lista_cadena(partes[1]);
                    }

                    if (partes[0].equals("get")) {
                        try {
                            File f = new File(partes[1]);
                            int t = (int) f.length();
                            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                            dos.writeUTF(f.getName());
                            dos.writeInt(t);

                            FileInputStream fis = new FileInputStream(partes[1]);
                            BufferedOutputStream bos;
                            try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                                bos = new BufferedOutputStream(socket.getOutputStream());
                                byte[] buffer = new byte[t];
                                bis.read(buffer);
                                for (int i = 0; i < buffer.length; i++) {
                                    bos.write(buffer[i]);
                                }
                            }
                            bos.close();
                            socket.close();
                        } catch (IOException e){
                            System.out.println(e);
                        }
                    }

                    salida.println(cadena);

                    System.out.println(cadena);
                    cadena = cadena.toUpperCase();
                    //salida.println(cadena);
                    System.out.println(cadena);
                    //salida.println(entrada.nextLine().toUpperCase());
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                System.out.println("Conexion cerrada");
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            ServerSocket listener = new ServerSocket(9095);
            while (true) {
                service.execute(new Mayusculas(listener.accept()));

            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static String lista_cadena(String s) {
        String cadena = null;
        File file = new File(s);
        File[] dir = file.listFiles();
        for (File f : dir) {
            cadena = cadena + "\n" + f.getName();
        }
        return cadena;
    }

}
