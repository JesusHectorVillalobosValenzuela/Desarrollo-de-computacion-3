#!/usr/bin/env python3
import socket
import pickle


def primos(inicio, final):
    numeros_primos = list()
    for num in range(inicio,final+1):
        if num > 1:
            for i in range(2,num):
                if(num % i == 0):
                    break;
            else:
                numeros_primos.append(num)
    return numeros_primos


def listen(HOST, PORT):
    c = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    c.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    c.bind((HOST, PORT))
    c.listen(10)
    print("Esperando conexion en puerto %d" % PORT)
    while True:
        current,address = c.accept()
        print("Conectado a %s" % address)
    while True:
        data = current.recv(2048)
        if (data ==b"stop"):
            current.shutdown()
            current.close()
            break
        elif data:
            #print(data)
            #mayusculas = data.upper()

            rango = data.split(",")
            inicio = int(rango[0])
            final = int(rango[1])
            lista = primos(inicio,final)
            data.pickle.dumps(lista)
            current.sendall(data)

            #print(mayusculas)
            #current.sendall(mayusculas)


if __name__ == '__main__':
    listen('',2424)
