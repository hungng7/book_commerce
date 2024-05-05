package com.example.book_commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private String bookId;
    private Long quantity;
    private Long quantityPrice;
    @ManyToOne
    @JoinColumn(name = "fk_accountId")
    private UserAccount userAccount;

    public Cart(String bookId, Long quantity, Long quantityPrice, UserAccount userAccount) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.quantityPrice = quantityPrice;
        this.userAccount = userAccount;
    }
}
