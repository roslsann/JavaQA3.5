package ru.netology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    static ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);
    static Product smartphone1 = new Smartphone(1, "S21", 700, "Samsung");
    static Product smartphone2 = new Smartphone(5, "iPhone12", 1000, "Apple");
    static Product smartphone3 = new Smartphone(7, "iPhone11", 900, "Apple");
    static Product book1 = new Book(5, "Война и Мир", 1000, "Толстой");
    static Product book2 = new Book(1, "Преступление и наказание", 500, "Достоевский");
    static Product book3 = new Book(3, "Фауст", 700, "Гете");


    @BeforeAll
    static void setUp() {
        repo.save(smartphone1);
        repo.save(smartphone2);
        repo.save(smartphone3);
        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
    }

    @Test
    public void shouldFindBookName() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Война и Мир");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookAuthor() {
        Product[] expected = {book2};
        Product[] actual = manager.searchBy("Достоевский");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneManufacturer() {
        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneName() {
        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("S21");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneIfSameManufacturer() {
        Product[] expected = {smartphone2, smartphone3};
        Product[] actual = manager.searchBy("Apple");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphone() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Xiaomi");

        assertArrayEquals(expected, actual);
    }

}
