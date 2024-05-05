package com.example.book_commerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserAccount {
    @Id
    @Column(nullable = false,unique = true)
    private String username;
    private String password;
}
