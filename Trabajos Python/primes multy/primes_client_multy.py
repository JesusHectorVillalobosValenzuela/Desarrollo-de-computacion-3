#!/usr/bin/env python3
import socket
import zlib
import pickle
HOST = "148.225.60.125"
PORT = 2424

def main():
  print("Intentando conectar a %s " %HOST)
  with socket.socket(socket.AF_INET,socket.SOCK_STREAM) as s:
    s.connect((HOST,PORT))
    while True:
      mensaje = input("Inserta rangos: ").encode("UTF-8")
      if not mensaje:
        s.sendall(b'stop')
        break

      lista_general = list()
      s.sendall(mensaje)
      z = zlib
      data = z.decompress(s.recv(2048))
      lista_primos = pickle.loads(data)
      lista_general.extend((lista_primos))

      print(lista_general)


if __name__ == '__main__':
    main()
