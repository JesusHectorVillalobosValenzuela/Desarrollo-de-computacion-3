import threading
import time
from bs4 import BeautifulSoup
import concurrent.futures
import pickle


def procesa_html(movie_html, diccionario_movies):

    soup = BeautifulSoup(movie_html, "html.parser")
    titulo = soup.title.text
    resume = soup.findAll("div", class_='inline canwrap')
    descripcion = resume[0].text
    palabras = descripcion.split(" ")

    for palabra in palabras:
        if palabra in diccionario_movies:
            diccionario_peliculas = diccionario_movies[palabra]
            if titulo not in diccionario_peliculas:
                diccionario_peliculas[titulo] = descripcion
            diccionario_movies[palabra] = diccionario_peliculas
        else:
            diccionario_peliculas[titulo] = descripcion
            diccionario_movies[palabra] = diccionario_peliculas






def procesa_lista(lista_movies):
    diccionario = dict()
    hilos = list()  # hilos = []
    max_worker = 3
    with concurrent.futures.ThreadPoolExecutor(max_worker) as executor:
        for movie in lista_movies:
            with open(movie + ".html", "r") as archivo:
                html = archivo.read()
            e = executor.submit(procesa_html, html, diccionario)
            hilos.append(e)

    return diccionario


def main():
    lista = ["tt0068163", "tt0068164", "tt0068165", "tt0068166", "tt0068167",
             "tt0068168", "tt0068169", "tt0068170", "tt0068171", "tt0068172",
             "tt0068173", "tt0068174", "tt0068175", "tt0068176", "tt0068177"]

    inicio = time.time()
    diccionario_movies = procesa_lista(lista)

    llave = "star"

    print("\n")
    if llave in diccionario_movies:
        print(diccionario_movies[llave])
    else:
        print("no existe")

    tiempo = time.time() - inicio
    print("\ntiempo en segundos:%f" % tiempo + "\n")

    with open("diccionario.pkl", "wb") as archivo:
        pickle.dump(diccionario_movies, archivo)


if __name__ == '__main__':
    main()
