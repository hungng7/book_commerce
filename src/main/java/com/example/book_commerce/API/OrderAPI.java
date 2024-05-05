package com.example.book_commerce.API;

import com.example.book_commerce.Model.*;
import com.example.book_commerce.Service.BookService;
import com.example.book_commerce.Service.CartService;
import com.example.book_commerce.Service.OrderDetailService;
import com.example.book_commerce.Service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderAPI {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @GetMapping("/orderList/{username}")
    public List<Order> getAllOrders(@PathVariable("username") String username){
        UserAccount userAccount = UserAccount.builder()
                .username(username)
                .build();
        List<Order> orders = orderService.getAll(username);
        return orders;
    }

}