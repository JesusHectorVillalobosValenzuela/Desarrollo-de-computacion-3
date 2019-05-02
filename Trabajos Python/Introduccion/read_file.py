def main():
    with open ("SuperHeroes.txt","r") as file:
        datos = file.read()
        heroes = datos.splitlines()
        print(heroes)

if __name__ == "__main__":
    main()
