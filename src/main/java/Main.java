import second.Book;
import second.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(5, 25); i++) {
            List<Book> books = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(5, 25); j++) {
                String title = faker.book().title();
                String author = faker.book().author();
                int year = faker.number().numberBetween(1400, 2025);
                int pages = faker.number().numberBetween(15, 1000);
                books.add(new Book(title, author, year, pages));
            }
            String name = faker.name().fullName();
            students.add(new Student(name, books));
        }

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .findFirst()
                .map(Book::getYear) // получить год выпуска, если книга есть
                .ifPresentOrElse(
                        year -> System.out.println("\n\nГод выпуска найденной книги: " + year),
                        () -> System.out.println("\n\nКниги после 2000 года отсутствуют.")
                );
    }
}
