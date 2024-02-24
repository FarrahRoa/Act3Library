import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean borrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getRunningTime() {
        return runningTime;
    }
}

abstract class LibraryUser {
    public abstract void borrowItem(LibraryItem item);
    public abstract void returnItem(LibraryItem item);
    public abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    private String name;
    private List<LibraryItem> borrowedItems;

    public Student(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        }
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Student : " + name);
        System.out.println("Borrowed Items : ");
        if (!borrowedItems.isEmpty()) {
            for (LibraryItem item : borrowedItems) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    System.out.println("- Book : " + book.getTitle() + " by " + book.getAuthor());
                } else if (item instanceof DVD) {
                    DVD dvd = (DVD) item;
                    System.out.println("- DVD : " + dvd.getTitle() + " by " + dvd.getDirector());
                }
            }
        } else {
            System.out.println("No items borrowed.");
        }
    }
}

class Teacher extends LibraryUser {
    private String name;
    private List<LibraryItem> borrowedItems;

    public Teacher(String name) {
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        }
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Teacher : " + name);
        System.out.println("Borrowed Items : ");
        if (!borrowedItems.isEmpty()) {
            for (LibraryItem item : borrowedItems) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    System.out.println("- Book : " + book.getTitle() + " by " + book.getAuthor());
                } else if (item instanceof DVD) {
                    DVD dvd = (DVD) item;
                    System.out.println("- DVD : " + dvd.getTitle() + " by " + dvd.getDirector());
                }
            }
        } else {
            System.out.println("No items borrowed.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentName);

        System.out.print("Enter teacher name: ");
        String teacherName = scanner.nextLine();
        Teacher teacher = new Teacher(teacherName);

        Book book1= new Book("Beginning Programming With Java for Dummies", "Barry A. Burd", 2018);
        Book book2 = new Book("Clean Code", "Robert Cecil Martin", 2008);
        Book book3 = new Book ("Java: A Beginner's Guide", "Herbert Schildt", 2002);
        DVD dvd1 = new DVD("Avengers: The Endgame", "Anthony Russo, Joe Russo", 180);
        DVD dvd2 = new DVD ("A Walk to Remember", "Adam Shankman", 102);

        // Borrowing items
        student.borrowItem(book1);
        student.borrowItem(book2);
        student.borrowItem(dvd2);
        teacher.borrowItem(dvd1);
        teacher.borrowItem(book3);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();
 

        scanner.close();
    }
}