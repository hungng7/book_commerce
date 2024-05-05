package com.example.book_commerce.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAccountDTO {
    private String username;
    private String password;
    @Builder
    public UserAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
