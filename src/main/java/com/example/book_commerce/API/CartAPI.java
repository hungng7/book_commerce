package com.example.book_commerce.API;

import com.example.book_commerce.DTO.BookDTO;
import com.example.book_commerce.DTO.UserAccountDTO;
import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Model.Cart;
import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.BookService;
import com.example.book_commerce.Service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartAPI {
    @Autowired
    private CartService cartService;
    @Autowired
    private HttpSession session;
    @Autowired
    private BookService bookService;

    @GetMapping("/cartList/{username}")
    public List<Cart> getAllCarts(@PathVariable("username") String username){
        List<Cart> cartList = cartService.getAll(username);
        List<Book> bookList = new ArrayList<>();
        long totalPrice = 0;
        for (Cart carts: cartList) {
            Book book = bookService.getWithId(carts.getBookId());
            bookList.add(book);
            totalPrice+=carts.getQuantityPrice();
        }
        return cartList;
    }

    @PostMapping("/cartList/{username}/add/{id}/{quantity}/{price}")
    public void addCart(HttpServletRequest request,
                        @PathVariable("username") String username,
                        @PathVariable("id") String bookId,
                        @PathVariable("quantity") Long quantity,
                        @PathVariable("price") Long price){

        Book book = Book.builder()
                .id(Long.valueOf(bookId))
                .price(price)
                .build();

        long quantityPrice = book.getPrice();

        UserAccount userAccount = UserAccount.builder()
                .username(username)
                .build();

        Cart cart = cartService.getWithUserAndBookId(bookId,userAccount);

        if (cart!=null){
            cart.setQuantity(quantity);
            cart.setQuantityPrice(cart.getQuantityPrice()+quantityPrice);
            cartService.save(cart);
        } else{
            cartService.save(new Cart(bookId,1L,quantityPrice,userAccount));
        }
        List<Cart> cartList = cartService.getAll(userAccount.getUsername());
        List<Book> bookList = new ArrayList<>();
        long totalPrice = 0;
        for (Cart carts: cartList) {
            Book b = bookService.getWithId(carts.getBookId());
            bookList.add(b);
            totalPrice+=carts.getQuantityPrice();
        }
    }

    @DeleteMapping("/cartList/{username}/remove/{id}")
    public void removeBook(@PathVariable("username") String username,
                             @PathVariable("id") String bookId){
        UserAccount userAccount = UserAccount.builder()
                .username(username)
                .build();
        Cart cart = cartService.getWithUserAndBookId(bookId,userAccount);
        if (cart!=null){
            cartService.delete(cart);
        }
    }

    @DeleteMapping("cartList/{username}/deleteAll")
    public void removeAllBook(@PathVariable("username") String username){
        UserAccount account = UserAccount.builder()
                .username(username)
                .build();
        List<Cart> cartList = cartService.getAll(account.getUsername());
        for (Cart cart : cartList){
            cartService.delete(cart);
        }
    }
}
