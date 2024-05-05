package com.example.book_commerce.Controller;

import com.example.book_commerce.Model.*;
import com.example.book_commerce.Service.BookService;
import com.example.book_commerce.Service.CartService;
import com.example.book_commerce.Service.OrderDetailService;
import com.example.book_commerce.Service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
public class OrderController {
    CartService cartService;
    BookService bookService;
    OrderDetailService orderDetailService;
    OrderService orderService;
    @PostMapping("/order")
    public String showOrder(Model model,HttpServletRequest request,HttpSession session){

        UserAccount userAccount = (UserAccount) session.getAttribute("account");
        if (userAccount != null){
            long total = 0;
            List<Cart> cartList = cartService.getAll(userAccount.getUsername());
            for (Cart carts: cartList) {
                total += carts.getQuantityPrice();
            }

            Order order = orderService.save(new Order(LocalDate.now(),total,userAccount));
            List<OrderDetail> orderDetails = new ArrayList<>();

            for (Cart carts: cartList){
                Book book = bookService.getWithId(carts.getBookId());
                OrderDetail orderDetail = new OrderDetail(1L,book.getPrice(),
                        carts.getQuantity(),
                        carts.getQuantityPrice(),
                        order,
                        book);
                orderDetails.add(orderDetail);
                orderDetailService.save(orderDetail);
            }
            model.addAttribute("orderDetailList",orderDetails);
            model.addAttribute("order", order);
            model.addAttribute("payerName", request.getParameter("payerName"));
            model.addAttribute("phone", request.getParameter("phone"));
            model.addAttribute("payerEmail", request.getParameter("payerEmail"));
            model.addAttribute("payerAddress", request.getParameter("payerAddress"));
            return "order";
        }
        return "/";
    }

}
