package hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Customer implements Serializable {
    private int customerId;
    private String customerName;
    private String phone;
}