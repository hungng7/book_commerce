package com.example.book_commerce.Service;

import com.example.book_commerce.Model.Order;
import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Repository.OrderRepository;
import com.example.book_commerce.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    public Order save(Order o){
        return orderRepository.save(o);
    }

    public List<Order> getAll(String username){
        UserAccount account = userAccountRepository.findByUsername(username);
        return orderRepository.findByUserAccount(account);
    }
}