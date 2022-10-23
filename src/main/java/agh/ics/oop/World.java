package agh.ics.oop;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        out.println("Start");
        Animal animal = new Animal();
        OptionsParser parser = new OptionsParser();

        for (MoveDirection direction: parser.parse(args)) {
            animal.move(direction);
        }

        out.println(animal);
        out.println("Stop");

        // Odpowiedź do zadania 10
        // Możemy utworzyć tablicę dwuwymiarową 5 x 5. Tablica[i][j] zawierała by informację true jeśli na współrzędnych
        // (i, j) występuje już zwierzę lub false w przeciwnym przypadku. W trakcie próby wykonania ruchu gdy sprawdzamy
        // czy zwierzę nie wyszło poza mapę dodajemy warunek czy na konkretnych współrzędnych znajduje się inne zwierze.
        // Jeśli tak to zwracamy false i nie zmieniamy aktualnego położenia zwierzęcia w przeciwnym razie
        // przesuwamy zwierzę i modyfikujemy tablice w taki sposób że Tablica[x][y] gdzie x,y to byly współrzędne
        // na których znajdowało się początkowo zwierzę ustawiamy na false a Tablica[i][j] ustawiamy na True.
    }
}
