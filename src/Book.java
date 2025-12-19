import java.time.Year;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available;
    private static int idGen = 1;

    // Default constructor
    public Book() {
        this.id = idGen++;
        available = true;
    }

    // Construct with parameters
    public Book(String title, String author, int year) {
        this();
        setAuthor(author);
        setTitle(title);
        setYear(year);
    }


    // Checking input
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("author must not be null or empty");
        }
        this.author = author;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title must not be null or empty");
        }
        this.title = title;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1500 || year > currentYear) {
            throw new IllegalArgumentException(
                    "year must be between 1500 and " + currentYear
            );
        }
        this.year = year;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }


    // Store information
    public void markAsBorrowed() {
        this.available = false;
    }

    public void markAsReturned() {
        this.available = true;
    }


    @Override
    public String toString() {
        return "Book{id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", available=" + available +
                '}';
    }


}
