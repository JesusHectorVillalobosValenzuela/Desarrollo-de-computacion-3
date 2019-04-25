#!/usr/bin/env python3
import socket
HOST = "148.225.60.125"
PORT = 2424

def main():
  print("Intentando conectar a %s " %HOST)
  with socket.socket(socket.AF_INET,socket.SOCK_STREAM) as skt:
    skt.connect((HOST,PORT))
    while True:
      mensaje = input("Mensaje: ").encode("UTF-8")
      if not mensaje:
        skt.sendall(b'stop')
        break
      skt.sendall(mensaje)
      list = skt.recv(2048)

      print(len(list))

      #data = s.recv(2048)
      #print(data)



if __name__ == '__main__':
    main()
