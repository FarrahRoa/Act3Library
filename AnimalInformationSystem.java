import java.io.*;
import java.util.*;

class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}


class Reptiles extends Animal {
    private String breed;

    public Reptiles(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public String toString() {
        return super.toString() + ", Breed: " + breed;
    }
}


class Bird extends Animal {
   private String type;

    public Bird(String name, int age, String type) {
        super(name, age);
        this.type= type;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}


class Mammals extends Animal {
    private String species;

    public Mammals(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }

    @Override
    public String toString() {
        return super.toString() + ", Species: " + species;
    }
}


class AnimalManager {
    private List<Animal> animals;
    private Scanner scanner;

    public AnimalManager() {
        animals = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void displayAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
    
    public void saveAnimalsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Animal animal : animals) {
                writer.println(animal.getClass().getSimpleName() + "," + animal.getName() + "," + animal.getAge());
            }
            System.out.println("Animal data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving animal data to file: " + e.getMessage());
        }
    }

    public void loadAnimalsFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String type = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    switch (type) {
                        case "Reptiles":
                            addAnimal(new Reptiles(name, age, parts[3]));
                            break;
                        case "Bird":
                            addAnimal(new Bird(name, age, parts[3]));
                            break;
                        case "Mammals":
                            addAnimal(new Mammals(name, age, parts[3]));
                            break;
                        default:
                            System.err.println("Unknown animal type: " + type);
                    }
                }
            }
            System.out.println("Animal data loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }

    public void menu() {
        int answer = -1; // Initialize answer to a non-zero value
while (answer != 0) { // Adjust the loop condition
    Cool();
    answer = input; // Correctly assign input to answer
    switch (answer) {
        case 1:
            addNewAnimal();
            break;
        case 2:
            displayAnimals();
            break;
        case 3:
            System.out.println("Enter the filename to save:");
            String filename = scanner.nextLine();
            saveAnimalsToFile(filename);
            break;
        case 4:
            System.out.println("Enter the filename to load:");
            String loadFilename = scanner.nextLine();
            loadAnimalsFromFile(loadFilename);
            break;
        case 0:
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice.");
    }
}

    }

    private static Scanner choice = new Scanner(System.in);
    private static int input;

    public static void Cool() {
        System.out.println("\nAnimal Information System Menu");
        System.out.println("1. Add a new animal");
        System.out.println("2. View animal information");
        System.out.println("3. Save animal data to file");
        System.out.println("4. Load animal data from file");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        input = choice.nextInt();
    }

    public void addNewAnimal() {
        System.out.println("\nAdd a new animal:");
        System.out.print("Enter animal type (Reptiles/Bird/Mammals): ");
        String type = scanner.next();
        System.out.print("Enter animal name: ");
        String name = scanner.next();
        System.out.print("Enter animal age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (type) {
            case "Reptiles":
                System.out.print("Enter Reptile breed: ");
                String breed = scanner.nextLine();
                addAnimal(new Reptiles(name, age, breed));
                break;
            case "Bird":
                System.out.print("Enter Bird type: ");
                String birdType = scanner.next();
                addAnimal(new Bird(name, age, birdType));
                break;
            case "Mammals":
                System.out.print("Enter Mammal species: ");
                String species = scanner.next();
                addAnimal(new Mammals(name, age, species));
                break;
            default:
                System.out.println("Invalid animal type.");
        }
    }
}

public class AnimalInformationSystem {
    public static void main(String[] args) {
        AnimalManager animalManager = new AnimalManager();
        animalManager.menu();
    }
}
