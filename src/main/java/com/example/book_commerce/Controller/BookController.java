package com.example.book_commerce.Controller;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
public class BookController {
    BookService bookService;
    HttpSession httpSession;

    @GetMapping("/")
    String home(Model model){
        if(httpSession.getAttribute("account")!=null) {
            List<Book> listBook = bookService.getAll();
            List<Book> first6Books = listBook
                    .stream()
                    .limit(6)
                    .collect(Collectors.toList());
            model.addAttribute("listBook",first6Books);
            return "index";
        }
        return "login";
    }

    @PostMapping("/books")
    String filters( @RequestParam(value = "category",defaultValue = "") List<String> categories,
                    @RequestParam(value = "brand",defaultValue = "") List<String> brands,
                    @RequestParam(value = "color",defaultValue = "") List<String> colors,
                    @RequestParam(value = "minPrice",defaultValue = "0") long minPrice,
                    @RequestParam(value = "maxPrice",defaultValue = "999999999") long maxPrice,
                    Model model){

        List<Book> listWithCategory = bookService.getWithCategories(categories);
        List<Book> listWithBrand = bookService.getWithBrands(brands);
        List<Book> listWithColor = bookService.getWithColors(colors);
        List<Book> listWithPriceRange = bookService.getWithPriceRange(minPrice,maxPrice);

        List<Book> resultFilter =
                bookService.insertBookList( listWithCategory,
                        bookService.insertBookList(listWithBrand,
                                bookService.insertBookList(listWithPriceRange,listWithColor)));

        model.addAttribute("listBook",resultFilter);

        return "index";
    }

    @GetMapping("/bookName")
    String getBookWithNameSearching(){
        return "redirect:/";
    }

    @PostMapping("/bookName")
    String postBookWithNameSearching(
            @RequestParam(value = "bookSearch") String bookSearch,
            Model model
    ){
        List<Book> listBookByName =bookService.getWithName(bookSearch);
        model.addAttribute("listBook",listBookByName);
        return "index";
    }

    @GetMapping("/bookDetail")
    String getDetailBook(@RequestParam(value = "id") String bookId, Model model){
        Book book  = bookService.getWithId(bookId);
        model.addAttribute(book);
        return "bookDetail";
    }
}
