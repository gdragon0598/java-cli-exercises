package tuan.com.model;

import lombok.*;
import tuan.com.type.BookStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private int id;
    private String name;
    private String auth;
    private String desc;
    private BookStatus status;

    public Book(int id, String name, String auth, String desc) {
        this.id = id;
        this.name = name;
        this.auth = auth;
        this.desc = desc;
        this.status = BookStatus.Available;
    }
}
