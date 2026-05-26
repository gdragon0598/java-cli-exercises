import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    private static final List<Book> bookList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        bookList.add(new Book("B001", "Tat Den", "Ngo Tat To", 5));
        bookList.add(new Book("B002", "Truyen Kieu", "Nguyen Du", 300));

        int choice;
        do {
            printMenu();
            System.out.print("Nhap lua chon (1-5): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Vui long nhap so hop le! Nhap lai: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> showAllBooks();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
            System.out.println();
        } while (choice != 5);
    }

    private static void printMenu() {
        System.out.println("===== QUAN LY THU VIEN CHUONG TRINH =====");
        System.out.println("1. Them sach moi");
        System.out.println("2. Hien thi danh sach sach");
        System.out.println("3. Cap nhat thong tin sach");
        System.out.println("4. Xoa sach");
        System.out.println("5. Thoat");
        System.out.println("=========================================");
    }

    private static void addBook() {
        System.out.println("\n--- THEM SACH MOI ---");
        System.out.print("Nhap ma sach (ID): ");
        String id = scanner.nextLine();

        if (findBookById(id) != null) {
            System.out.println("Loi: Ma sach nay da ton tai!");
            return;
        }

        System.out.print("Nhap ten sach: ");
        String title = scanner.nextLine();
        System.out.print("Nhap tac gia: ");
        String author = scanner.nextLine();
        System.out.print("Nhap so luong: ");
        int quantity = scanner.nextInt();

        bookList.add(new Book(id, title, author, quantity));
        System.out.println("Them sach thanh cong!");
    }

    private static void showAllBooks() {
        System.out.println("\n--- DANH SACH SACH TRONG THU VIEN ---");
        if (bookList.isEmpty()) {
            System.out.println("Thu vien hien chua co sach.");
            return;
        }
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    private static void updateBook() {
        System.out.println("\n--- CAP NHAT THONG TIN SACH ---");
        System.out.print("Nhap ma sach can sua: ");
        String id = scanner.nextLine();
        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Khong tim thay ma sach: " + id);
            return;
        }

        System.out.print("Nhap ten sach moi (Bo trong neu giu nguyen): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.trim().isEmpty()) {
            book.setTitle(newTitle);
        }

        System.out.print("Nhap tac gia moi (Bo trong neu giu nguyen): ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.trim().isEmpty()) {
            book.setAuthor(newAuthor);
        }

        System.out.print("Nhap so luong moi (Nhap -1 neu giu nguyen): ");
        int newQuantity = scanner.nextInt();
        if (newQuantity >= 0) {
            book.setQuantity(newQuantity);
        }

        System.out.println("Cap nhat thong tin thanh cong!");
    }

    private static void deleteBook() {
        System.out.println("\n--- XOA SACH ---");
        System.out.print("Nhap ma sach can xoa: ");
        String id = scanner.nextLine();
        Book book = findBookById(id);

        if (book == null) {
            System.out.println("Khong tim thay sach can xoa.");
            return;
        }

        bookList.remove(book);
        System.out.println("Da xoa sach thanh cong!");
    }

    private static Book findBookById(String id) {
        for (Book b : bookList) {
            if (b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }
}