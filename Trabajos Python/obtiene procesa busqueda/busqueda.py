import pickle

def main ():
    # Leer diccionario
    with open ("diccionario.pkl", "rb") as archivo:
        d = pickle.load(archivo)
    # Ciclo
    llave = " "

    while (len(llave)>0):
        llave = input("Palabra a buscar:")
        if (len(llave)==0):
            break
        else:
            if(llave in d):
                resultado = d[llave]
                for r in resultado:
                    print(r)
            else:
                if (llave == "exit"):
                    exit()
                else:
                    print("Palabra no encontrada")


if __name__ == '__main__':
    main()