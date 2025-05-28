import java.util.*;

// --- Custom Exceptions ---
class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}

// --- Book Class ---
class Book {
    private String title;
    private String author;
    private String bookId;
    private int copies;

    public Book(String title, String author, String bookId, int copies) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.copies = copies;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return copies > 0;
    }

    public void borrowBook() throws BookNotAvailableException {
        if (copies <= 0) throw new BookNotAvailableException("Book not available.");
        copies--;
    }

    public void returnBook() {
        copies++;
    }

    public void display() {
        System.out.println("[" + bookId + "] " + title + " by " + author + " | Copies: " + copies);
    }
}

// --- Abstract User Class ---
abstract class User {
    protected String userId;
    protected String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    abstract void borrowBook(Library library, String bookId) throws Exception;
    abstract void returnBook(Library library, String bookId);
}

// --- Student Class ---
class Student extends User {
    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    void borrowBook(Library library, String bookId) throws Exception {
        library.issueBook(bookId);
        System.out.println(name + " borrowed book: " + bookId);
    }

    @Override
    void returnBook(Library library, String bookId) {
        library.receiveBook(bookId);
        System.out.println(name + " returned book: " + bookId);
    }
}

// --- Librarian Class ---
class Librarian extends User {
    public Librarian(String userId, String name) {
        super(userId, name);
    }

    @Override
    void borrowBook(Library library, String bookId) throws Exception {
        library.issueBook(bookId);
        System.out.println("Librarian " + name + " borrowed book: " + bookId);
    }

    @Override
    void returnBook(Library library, String bookId) {
        library.receiveBook(bookId);
        System.out.println("Librarian " + name + " returned book: " + bookId);
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println("Book added by librarian: " + book.getBookId());
    }
}

// --- Library Class ---
class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void issueBook(String bookId) throws BookNotAvailableException {
        Book book = books.get(bookId);
        if (book == null || !book.isAvailable()) {
            throw new BookNotAvailableException("Book with ID " + bookId + " not available.");
        }
        book.borrowBook();
    }

    public void receiveBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null) {
            book.returnBook();
        }
    }

    public User getUser(String userId) throws InvalidUserException {
        User user = users.get(userId);
        if (user == null) throw new InvalidUserException("User not found.");
        return user;
    }

    public void displayBooks() {
        for (Book book : books.values()) {
            book.display();
        }
    }
}

// --- Main Class ---
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add initial books
        library.addBook(new Book("Java Fundamentals", "James Gosling", "B101", 3));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "B102", 2));

        // Register users
        Student s1 = new Student("S01", "Alice");
        Librarian l1 = new Librarian("L01", "Mr. Sharma");
        library.registerUser(s1);
        library.registerUser(l1);

        System.out.println("Available books:");
        library.displayBooks();

        try {
            // Student borrows and returns a book
            User user1 = library.getUser("S01");
            user1.borrowBook(library, "B101");
            user1.returnBook(library, "B101");

            // Librarian adds a new book
            Librarian librarian = (Librarian) library.getUser("L01");
            librarian.addBook(library, new Book("Data Structures", "Mark Allen", "B103", 5));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nUpdated book list:");
        library.displayBooks();
    }
}
