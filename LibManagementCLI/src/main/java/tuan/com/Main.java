package tuan.com;

import tuan.com.utils.ScannerUtils;

public class Main {
    private static LibraryService libraryService = LibraryService.getLibraryService();
    public static void main(String[] args) {
        // DEMO 2 User add books
        libraryService.insertMockData();
        Thread t1 = new Thread(new User1(), "Luong 1");
        Thread t2 = new Thread(new User2(), "Luong 2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //
        menuOptions();
    }

    public static void menuOptions() {
        boolean open = true;

        while (open) {
            System.out.println("\n================ Lib ================");
            System.out.println("1. Add new book.");
            System.out.println("2. Borrow book.");
            System.out.println("3. return book.");
            System.out.println("4. View available book.");
            System.out.println("5. Search book by title.");
            System.out.println("0. Exit App.");

            try {
                int choice = ScannerUtils.enterInteger("> Your choice: ");
                switch (choice) {
                    case 1 -> libraryService.addNewBook();
                    case 2 -> libraryService.borrowAvailableBook();
                    case 3 -> libraryService.returnBook();
                    case 4 -> libraryService.viewAvailableBook();
                    case 5 -> libraryService.searchBookByTitle();
                    case 0 -> open = false;
                    default -> System.out.println("Invalid input!");
                }
            } catch (RuntimeException e) {
                System.out.println("Menu: " + e.getMessage());
            }
        }
    }
}