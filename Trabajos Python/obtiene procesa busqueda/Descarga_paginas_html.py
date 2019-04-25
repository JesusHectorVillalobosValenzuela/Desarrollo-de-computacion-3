import requests
import threading
from bs4 import BeautifulSoup

def descarga(codigo):
  URL = "http://www.imdb.com/title/"+codigo
  try:
    ans = requests.get(URL)
    pag = ans.content
    archivo_html = codigo+".html"
    with open (archivo_html,"wb") as archivo:
      archivo.write(pag)
    print("Archivo %s escrito" % archivo_html)
  except OSError as e:
    print(archivo_html)
    print(e.strerror)

def lee(lista):
  for peli in lista:
    archivo = peli+".html"
    with open(archivo, "r") as archivo:
      html = archivo.read()
    soup = BeautifulSoup(html,"html.parser")
    titulo = soup.title.string
    descript = soup.findAll("div",class_="inline canwrap")
    print(titulo)
    print(descript[0].text)


def main():
  lista = ["tt0068163","tt0068164","tt0068165","tt0068166","tt0068167",
           "tt0068168","tt0068169","tt0068170","tt0068171","tt0068172",
           "tt0068173","tt0068174","tt0068175","tt0068176","tt0068177"]

  for page in lista:
     t1 = threading.Thread(target=descarga, args=(page,))
     t1.start()
     #pelis[]=page
     #Done
   #print("AcabÈ")
  lee(lista)

if __name__ == '__main__':
    main()
