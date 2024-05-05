package com.example.book_commerce.Repository;

import com.example.book_commerce.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findBooksWithPriceRange(@Param("minPrice") long minPrice,
                                       @Param("maxPrice") long maxPrice);
    @Query("SELECT b FROM Book b WHERE b.brand IN :brands ")
    List<Book> findBooksWithBrands(@Param("brands") List<String> brands);
    @Query("SELECT b FROM Book b WHERE b.category IN :categories ")
    List<Book> findBooksWithCategories(@Param("categories")List<String> categories);
    @Query("SELECT b FROM Book b WHERE b.color IN :colors")
    List<Book> findBooksWithColors(@Param("colors") List<String> colors);

    @Query("SELECT b FROM Book b WHERE b.name like %:key%")
    List<Book> findBooksWithNameKey(@Param("key")String name);

}