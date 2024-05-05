package com.example.book_commerce.Repository;

import com.example.book_commerce.Model.Cart;
import com.example.book_commerce.Model.Order;
import com.example.book_commerce.Model.UserAccount;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByUserAccount(UserAccount account);
}
