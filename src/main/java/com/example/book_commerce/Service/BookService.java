package com.example.book_commerce.Service;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public List<Book> getWithBrands(List<String> brand){
        return bookRepository.findBooksWithBrands(brand);
    }
    public List<Book> getWithPriceRange(Long min, Long max){
        return bookRepository.findBooksWithPriceRange(min,max);
    }
    public List<Book> getWithCategories(List<String> category){
        return bookRepository.findBooksWithCategories(category);
    }
    public List<Book> getWithColors(List<String> color){

        return bookRepository.findBooksWithColors(color);
    }
    public List<Book> getWithName(String name){
        return bookRepository.findBooksWithNameKey(name);
    }
    public void save(List<Book> b){
        bookRepository.saveAll(b);
    }
    public Book getWithId(String id){
        return bookRepository.getReferenceById(id);
    }

    public List<Book> insertBookList(List<Book> b1, List<Book> b2){
        if (b1.isEmpty()){return b2;}
        if (b2.isEmpty()){return b1;}

        return b1.stream()
                .filter(b2::contains)
                .distinct()
                .collect(Collectors.toList());
    }
}
