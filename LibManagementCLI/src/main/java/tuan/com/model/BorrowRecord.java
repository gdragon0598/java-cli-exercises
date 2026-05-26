package tuan.com.model;

import lombok.*;
import tuan.com.type.BorrowRecordStatus;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecord {
    private int id;
    private Book book;
    private LocalDate from;
    private LocalDate to;
    private BorrowRecordStatus status;

    public BorrowRecord(int id, Book book, LocalDate from, LocalDate to) {
        this.id = id;
        this.book = book;
        this.from = from;
        this.to = to;
        this.status = BorrowRecordStatus.Borrowed;
    }
}
