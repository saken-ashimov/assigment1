import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class LibraryApp {
    private final List<Book> books = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        System.out.println("Welcome to Library App!");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose option: ");
            switch (choice) {
                case 1 -> printAllBooks();
                case 2 -> addNewBook();
                case 3 -> searchBooksByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBookById();
                case 7 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println(); // пустая строка для читабельности
        }
    }
    private void printMenu() {
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }
    // Print all books
    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("In the library no books");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }
    // Add new book
    private void addNewBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        int year = readInt("Year: ");
        try {
            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Added: " + book);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // 3) Search books by title
    private void searchBooksByTitle() {
        System.out.print("Enter part of the title: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            String t = b.getTitle();
            if (t != null && t.toLowerCase().contains(query)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }
    // 4) Borrow a book
    private void borrowBook() {
        int id = readInt("Enter book id to borrow: ");
        Book book = findById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isAvailable()) {
            book.markAsBorrowed();
            System.out.println("Borrowed: " + book);
        } else {
            System.out.println("This book is already borrowed.");
        }
    }
    // 5) Return a book
    private void returnBook() {
        int id = readInt("Enter book id to return: ");
        Book book = findById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (!book.isAvailable()) {
            book.markAsReturned();
            System.out.println("Returned: " + book);
        } else {
            System.out.println("This book is already available (not borrowed)");
        }
    }
    // 6) Delete a book by id
    private void deleteBookById() {
        int id = readInt("Enter book id to delete: ");
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getId() == id) {
                it.remove();
                System.out.println("Deleted book with id = " + id);
                return;
            }
        }
        System.out.println("Book not found.");
    }
    // --- helpers ---
    private Book findById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }


    // read int
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
}
