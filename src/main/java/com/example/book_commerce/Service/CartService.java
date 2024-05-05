package com.example.book_commerce.Service;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Model.Cart;
import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Repository.CartRepository;
import com.example.book_commerce.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    public List<Cart> getAll(String username){
        UserAccount account = userAccountRepository.findByUsername(username);
        return cartRepository.findByUserAccount(account);
    }
    public Cart getWithUserAndBookId(String bookId, UserAccount user){
        return cartRepository.findByBookIdAndUserAccount(bookId, user);
    }
    public void save(Cart c){
        cartRepository.save(c);
    }
    public void delete(Cart c){cartRepository.delete(c);}
}
