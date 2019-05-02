#!/usr/bin/env python3
import socket
import pickle
import multiprocessing
import zlib

HOST = ''
PORT = 2424


def primos(inicio, final):
    numeros_primos = list()
    for num in range(inicio,final+1):
        if num > 1:
            for i in range(2,num):
                if(num % i == 0):
                    break
            else:
                numeros_primos.append(num)
    return numeros_primos


def main ():
    c = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    c.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    c.bind((HOST, PORT))
    c.listen(10)
    print("Esperando conexion en puerto %d" % PORT)
    while True:
        current, address = c.accept()
        print("Conectado a %s" % address[0])

        while True:
            data = current.recv(2048)
            if data == "stop":
                current.shutdown(1)
                current.close()
                exit()
            else:
                rango = data.split(b",")
                print(rango)
                inicio = int(rango[0])
                final = int(rango[1])

                if final > 7500:
                    print("Procesando primos entre %d y %d" % (inicio, final))
                    list_ents = []
                    for i in range(inicio,final,7500):
                        list_ents.append(i)
                    for i,e in enumerate(list_ents[:-1]):
                        bgn = e
                        end = list_ents[i+1]
                        list_primes = primos(bgn,end)
                        data = pickle.dumps(list_primes)
                        z = zlib.compress(data)
                    current.sendall(z)


                else:
                    print("Procesando primos entre %d y %d" % (inicio, final))
                    lista = primos(inicio, final)
                    data = pickle.dumps(lista)
                    z = zlib.compress(data)
                    current.sendall(z)




if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        pass