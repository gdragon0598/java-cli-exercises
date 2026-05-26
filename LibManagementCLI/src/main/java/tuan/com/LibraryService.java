package tuan.com;

import tuan.com.model.Book;
import tuan.com.model.BorrowRecord;
import tuan.com.type.BookStatus;
import tuan.com.type.BorrowRecordStatus;
import tuan.com.utils.ScannerUtils;
import tuan.com.utils.SearchEngine;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {
    private static LibraryService instance;
    private static final Map<Integer, Book> bookMap = new LinkedHashMap<>();
    private static final Map<Integer, BorrowRecord> borrowRecordMap = new LinkedHashMap<>();
    public static SearchEngine searchEngine = new SearchEngine();

    private LibraryService(){}

    public static LibraryService getInstance() {
        if (instance == null) {
            instance = new LibraryService();
        }
        return instance;
    }

    public void insertMockData() {
        Book book1 = new Book(1, "Nhà Giả Kim", "Paulo Coelho",
                "Cuốn sách không chỉ là một câu chuyện phiêu lưu mà còn là một hành trình tìm kiếm ý nghĩa cuộc sống. Qua từng nhân vật và chi tiết biểu tượng, “Nhà Giả Kim” truyền tải thông điệp về việc lắng nghe trái tim, theo đuổi ước mơ và tin vào dấu hiệu của vũ trụ. Với văn phong giản dị, sâu sắc, tác phẩm đã trở thành nguồn cảm hứng cho hàng triệu độc giả trên toàn thế giới.");
        Book book2 = new Book(2, "Đi Tìm Lẽ Sống", " Viktor E. Frankl",
                "“Đi Tìm Lẽ Sống” là một cuốn sách nổi tiếng của Viktor E. Frankl, bác sĩ tâm thần học người Áo, kể lại những trải nghiệm của ông trong các trại tập trung của Đức Quốc xã trong Thế chiến thứ hai.");
        Book book3 = new Book(3, "Lược Sử Loài Người", "Yuval Noah Harari",
                "“Sapiens: Lược Sử Loài Người” là một cuốn sách phi hư cấu nổi bật của Yuval Noah Harari, kể lại hành trình tiến hóa và phát triển của loài người từ thời tiền sử đến xã hội hiện đại. Với cách tiếp cận mới mẻ và dễ hiểu, tác giả dẫn dắt người đọc qua ba cuộc cách mạng lớn – Cách mạng Nhận thức, Cách mạng Nông nghiệp và Cách mạng Khoa học – để lý giải vì sao loài Homo sapiens, vốn chỉ là một trong nhiều loài người, lại trở thành giống loài thống trị Trái Đất.");

        bookMap.put(1, book1);
        bookMap.put(2, book2);
        bookMap.put(3, book3);
        Book book = bookMap.get(1);
        book.setStatus(BookStatus.Borrowed);
        borrowRecordMap.put(1, new BorrowRecord(1, book, LocalDate.parse("2026-05-22"), LocalDate.parse("2026-05-26"),
                BorrowRecordStatus.Borrowed));

        searchEngine.addBooks(bookMap);
    }

    public synchronized void addNewBook() {
        int nextId = Collections.max(bookMap.keySet()) + 1;
        System.out.println("# Add Book");

        String[] requires = { "Enter name book: ", "Enter author: ", "Enter Description: " };
        String[] inputs = new String[requires.length];

        enterBookInfo(requires, inputs);

        Book book = new Book();

        book.setName(inputs[0]);
        book.setAuth(inputs[1]);
        book.setDesc(inputs[2]);

        bookMap.put(nextId, book);
        searchEngine.addBook(book);
    }

    public synchronized void addNewBook(String nameBook, String auth, String desc) {
        int nextId = Collections.max(bookMap.keySet()) + 1;
        Book book = new Book();

        book.setId(nextId);
        book.setName(nameBook);
        book.setAuth(auth);
        book.setDesc(desc);
        book.setStatus(BookStatus.Available);

        System.out.println("# Add Book > " + book);

        bookMap.put(nextId, book);
        searchEngine.addBook(book);
    }

    public void enterBookInfo(String[] requires, String[] inputs) {
        byte idx = 0;
        String input;

        do {
            input = ScannerUtils.enterString(requires[idx]).trim();
            inputs[idx] = input;
            idx++;
        } while (!input.equalsIgnoreCase("exit") &&
                !input.equalsIgnoreCase("quit") &&
                !input.isEmpty() &&
                idx < requires.length);
    }

    public void viewAvailableBook() {
        System.out.println("# Show Books");
        bookMap.forEach((k, e) -> {
            if (e.getStatus() == BookStatus.Available)
                System.out.println(k + " : " + e);
        });
    }

    public synchronized void borrowAvailableBook() {
        // try excep
        System.out.println("# Borrow Book");
        viewAvailableBook();
        try {
            int bookId = Integer.parseInt(ScannerUtils.enterString("Enter book id: "));
            LocalDate fromDate = ScannerUtils.enterLocalDate("From date (yyyy-mm-dd): ");
            LocalDate toDate = ScannerUtils.enterLocalDate("To date (yyyy-mm-dd): ");

            if (toDate.isBefore(fromDate)) {
                System.out.println("Invalid borrow date!");
                return;
            }

            Book book = bookMap.get(bookId);
            if (book == null) {
                System.out.println("Id book invalid");
                return;
            }

            int nextId = Collections.max(borrowRecordMap.keySet()) + 1;
            borrowRecordMap.put(nextId, new BorrowRecord(nextId, book, fromDate, toDate));
            book.setStatus(BookStatus.Borrowed);

            System.out.println("Borrow book success !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook() {
        System.out.println("# Return book");
        viewListBorrowRecord();

        int idBorrowRecord = Integer.parseInt(ScannerUtils.enterString("Enter id borrow record:"));

        if (!borrowRecordMap.containsKey(idBorrowRecord)) {
            System.out.println("Borrow record not found !!!");
            return;
        }

        BorrowRecord borrowRecord = borrowRecordMap.get(idBorrowRecord);
        borrowRecord.setStatus(BorrowRecordStatus.Returned);
        borrowRecord.getBook().setStatus(BookStatus.Available);
        borrowRecordMap.remove(idBorrowRecord);

        System.out.println("Return book success.");
    }

    public void viewListBorrowRecord() {
        System.out.println("List borrow record:");
        borrowRecordMap.forEach((k, v) -> System.out.println("Id: " + k + " -> " + v));
    }

    public void searchBookByTitle() {
        System.out.println("# Search book by title");
        String title = ScannerUtils.enterString("Enter title: ");

        List<Book> listBook = searchEngine.search(title);

        System.out.println("Find " + listBook.size());
        listBook.forEach(b -> System.out.println("id: " + b.getId() + " - " + b.getName() + " - " + b.getAuth()));
    }
}
