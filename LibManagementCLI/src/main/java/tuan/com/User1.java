package tuan.com;

import tuan.com.utils.BookData;

public class User1 implements Runnable {
    private final LibraryService libraryService = LibraryService.getLibraryService();
    @Override
    public void run() {
        System.out.println("User 1 Run");
        for (int i = 0; i < 10; ++i) {
            try {
                BookData.BookInfo book = BookData.FAMOUS_BOOKS[i % 10];
                System.out.println("Thread 1 add: " + book.title);
                libraryService.addNewBook(book.title, book.author + " -- U1", book.description);
                System.out.println("Thread 1 add: " + book.title + " SUCCESS");
            } catch (RuntimeException e) {
                System.out.println("Thread 1: " + e.getMessage());
            }
        }
    }
}
