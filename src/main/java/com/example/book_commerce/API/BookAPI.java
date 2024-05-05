package com.example.book_commerce.API;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookAPI {
    @Autowired
    private BookService bookService;

    @GetMapping(value = { "", "/" })
    public Iterable<Book> getBookList(){
        return bookService.getAll();
    }

    @PostMapping("/filters")
    public Iterable<Book> filters( @RequestParam(value = "category",defaultValue = "") List<String> categories,
                    @RequestParam(value = "brand",defaultValue = "") List<String> brands,
                    @RequestParam(value = "color",defaultValue = "") List<String> colors,
                    @RequestParam(value = "minPrice",defaultValue = "0") long minPrice,
                    @RequestParam(value = "maxPrice",defaultValue = "999999999") long maxPrice){

        List<Book> listWithCategory = bookService.getWithCategories(categories);
        List<Book> listWithBrand = bookService.getWithBrands(brands);
        List<Book> listWithColor = bookService.getWithColors(colors);
        List<Book> listWithPriceRange = bookService.getWithPriceRange(minPrice,maxPrice);

        List<Book> resultFilter =
                bookService.insertBookList( listWithCategory,
                        bookService.insertBookList(listWithBrand,
                                bookService.insertBookList(listWithPriceRange,listWithColor)));

        return resultFilter;
    }

    @PostMapping("/bookName")
    public Iterable<Book> BookWithNameSearching(
            @RequestParam(value = "bookSearch") String bookSearch
    ){
        List<Book> listBookByName = bookService.getWithName(bookSearch);
        return listBookByName;
    }
}
