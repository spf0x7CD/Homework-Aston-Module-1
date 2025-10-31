package second;

public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }


    @Override
    public String toString() {
        return String.format("[name: %s, author: %s, year: %s]", title, author, year);
    }

}
