package com.example.book_commerce.Repository;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Model.Cart;
import com.example.book_commerce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    List<Cart> findByUserAccount(UserAccount account);
    Cart findByBookIdAndUserAccount(String bookId, UserAccount account);
}
