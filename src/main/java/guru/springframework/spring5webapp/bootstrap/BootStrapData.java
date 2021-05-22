package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting the server");

        System.out.println("Creating all the objects");

        Book book1 = new Book("Science", "456");
        Author author1 = new Author("Amit", "Amitabh");
        Publisher publisher1 = new Publisher("Ankit","Patna", "Bihar", "Patna", "800020");

        System.out.println("Trying to save the publisher object");

        //Okay so if you remove this line below then you will get the flushing of transient objects prior to saving exception
        //It happens because some other object
        publisherRepository.save(publisher1);

        System.out.println("Adding all the joined objects into each other");

        book1.getAuthors().add(author1);
        author1.getBooks().add(book1);
        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        System.out.println("Trying to save all the other objects");

        authorRepository.save(author1);
        bookRepository.save(book1);
        publisherRepository.save(publisher1);

        System.out.println("Creating new sets of objects");

        Book book2 = new Book("Maths", "546");
        Author author2 = new Author("Anand", "Golub");
        Publisher publisher2 = new Publisher("Ankita","Ranchi", "Jharkhand", "Ranchi", "720020");

        publisherRepository.save(publisher2);

        book2.getAuthors().add(author2);
        author2.getBooks().add(book2);
        book2.setPublisher(publisher2);
        publisher2.getBooks().add(book2);

        authorRepository.save(author2);
        bookRepository.save(book2);
        publisherRepository.save(publisher2);

        System.out.println("Book count is " + bookRepository.count());
        System.out.println("Publisher number of count is" + publisherRepository.count());
    }
}
