def main():

    super = ["Thor","Captain Marvel","Iron man","Spiderman","Wonder woman"]

    with open("SuperHeroes.txt","w") as archivo:
        for l in super:
            archivo.write(l + "\n")
    mas_heroes = ["Hulk","Ant man"]

    with open ("SuperHeroes.txt","a") as archivo:
        for l in mas_heroes:
            archivo.write(l + "\n")

if __name__ == "__main__":
    main()
