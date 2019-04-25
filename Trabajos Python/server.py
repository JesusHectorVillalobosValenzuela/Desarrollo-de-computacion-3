#!/usr/bin/env python3
import socket

def listen(HOST,PORT):
    c = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    c.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
    c.bind((HOST,PORT))
    c.listen(10)
    print("Esperando conexion en puerto %d" %PORT)
    while True:
        current = c.accept()
        address = c.accept()
        print("Conectado a %s" %addres)
        while True:
            data = current.recv(2048)
            if (data == b'stop'):
                current.shutdown(1)
                current.close()
                break
            elif data:
                print(data)
                may = data.upper()
                print(may)
                current.sendall(may)


if __name__ == '__main__':
    listen("",9999)
