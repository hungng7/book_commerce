package com.example.book_commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private LocalDate createdDate;
    private Long total;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_username")
    private UserAccount userAccount;

    public Order(LocalDate createdDate, Long total, UserAccount userAccount) {
        this.createdDate = createdDate;
        this.total = total;
        this.userAccount = userAccount;
    }
}
