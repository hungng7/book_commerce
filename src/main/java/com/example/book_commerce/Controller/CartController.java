package com.example.book_commerce.Controller;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Model.Cart;
import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.BookService;
import com.example.book_commerce.Service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
public class CartController {
    CartService cartService;
    HttpSession httpSession;
    BookService bookService;
    @PostMapping("/cart")
    public String addCart(HttpServletRequest request,Model model){
        String bookId = request.getParameter("bookId");
        long quantityPrice = Long.valueOf(request.getParameter("quantityPrice"));
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        Cart cart = cartService.getWithUserAndBookId(bookId,account);
        if (cart!=null){
            cart.setQuantity(cart.getQuantity()+1);
            cart.setQuantityPrice(cart.getQuantityPrice()+quantityPrice);
            cartService.save(cart);
        } else{
            cartService.save(new Cart(bookId,1L,quantityPrice,account));
        }
        List<Cart> cartList = cartService.getAll(account.getUsername());
        List<Book> bookList = new ArrayList<>();
        long totalPrice = 0;
        for (Cart carts: cartList) {
            Book b = bookService.getWithId(carts.getBookId());
            bookList.add(b);
            totalPrice+=carts.getQuantityPrice();
        }
        model.addAttribute("bookList", bookList);
        model.addAttribute("cartList",cartList);
        model.addAttribute("totalPrice",totalPrice);
        return "cart";
    }

    @GetMapping("cart")
    public String getAllCarts(Model model){
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        List<Cart> cartList = cartService.getAll(account.getUsername());
        List<Book> bookList = new ArrayList<>();
        long totalPrice = 0;
        for (Cart carts: cartList) {
            Book book = bookService.getWithId(carts.getBookId());
            bookList.add(book);
            totalPrice+=carts.getQuantityPrice();
        }
        model.addAttribute("bookList", bookList);
        model.addAttribute("cartList",cartList);
        model.addAttribute("totalPrice",totalPrice);
        return "cart";
    }

    @GetMapping("cart/increase/{id}")
    public String increasingBook(@PathVariable("id") String bookId, Model model){
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        Cart cart = cartService.getWithUserAndBookId(bookId,account);
        Book book = bookService.getWithId(bookId);
        if (cart!=null){
            cart.setQuantity(cart.getQuantity()+1);
            cart.setQuantityPrice(cart.getQuantityPrice()+ book.getPrice());
            cartService.save(cart);
        } else{
            cartService.save(new Cart(bookId,1L,book.getPrice(),account));
        }
        return "redirect:/cart";
    }

    @GetMapping("cart/decrease/{id}")
    public String decreasingBook(@PathVariable("id") String bookId, Model model){
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        Cart cart = cartService.getWithUserAndBookId(bookId,account);
        Book book = bookService.getWithId(bookId);
        if (cart!=null){
            if (cart.getQuantity()>1){
                cart.setQuantity(cart.getQuantity()-1);
                cart.setQuantityPrice(cart.getQuantityPrice()- book.getPrice());
                cartService.save(cart);
            } else{
                cartService.delete(cart);
            }
        } else{
            cartService.save(new Cart(1L,bookId,1L,book.getPrice(),account));
        }
        return "redirect:/cart";
    }

    @GetMapping("cart/remove/{id}")
    public String removeBook(@PathVariable("id") String bookId, Model model){
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        Cart cart = cartService.getWithUserAndBookId(bookId,account);
        if (cart!=null){
            cartService.delete(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("cart/deleteAll")
    public String removeAllBook(Model model){
        UserAccount account = (UserAccount) httpSession.getAttribute("account");
        List<Cart> cartList = cartService.getAll(account.getUsername());
        for (Cart cart : cartList){
            cartService.delete(cart);
        }
        return "redirect:/";
    }

}
