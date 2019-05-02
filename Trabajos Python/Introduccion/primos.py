
import threading

def primos(inicio, final, mapa):
    numeros_primos = list()
    for num in range(inicio,final+1):
        if num > 1:
            for i in range(2,num):
                if(num % i == 0):
                    break;
            else:
                numeros_primos.append(num)

    mapa[inicio]=numeros_primos

def main():
    mapa_primos = dict()
    #primos(1,100,mapa_primos)
    #primos(101,200,mapa_primos)
    #print(mapa_primos)
    for i in range(0,1000,200):
        t = threading.Thread(target=primos, args=(i,i+200,mapa_primos))
        t.start()

    for k,v in mapa_primos.items():
        print(" ")
        print(k,v)

if __name__ == "__main__":
    main()
