package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("book1", 20);
        Book book2 = new Book("book2", 30);
        Book book3 = new Book("book3", 10);
        Book book4 = new Book("Clean code", 22);
        Book[] books = new Book[]{book1, book2, book3, book4};
        for (Book book : books) {
            System.out.println(book.getName());
        }
        Book tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;
        for (Book book : books) {
            System.out.println(book.getName());
        }
        for (Book book : books) {
            if ("Clean code".equals(book.getName())) {
                System.out.println(book.getName());
            }
        }
    }
}
