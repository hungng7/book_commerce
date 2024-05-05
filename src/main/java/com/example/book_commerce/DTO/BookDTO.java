package com.example.book_commerce.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private Long price;
    private String category;
    private String author;
    private String brand;
    private String color;
    private String image;
    private String description;
}
