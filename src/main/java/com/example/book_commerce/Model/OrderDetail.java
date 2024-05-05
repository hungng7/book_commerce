package com.example.book_commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long quantity;
    private Long quantityPrice;
    @ManyToOne
    @JoinColumn(name = "fk_orderId")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "fk_bookId")
    private Book book;
}
