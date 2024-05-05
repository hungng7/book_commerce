package com.example.book_commerce.Config;

import com.example.book_commerce.Model.Book;
import com.example.book_commerce.Repository.BookRepository;
import com.example.book_commerce.Service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner bookcommandLineRunner(BookService bookService){
        return args -> {
            Book b1 = new Book(1L,
                    "Dragon Ball",
                    30000L,
                    "Manga",
                    "Akira Toriyama",
                    "Toei",
                    "white",
                    "https://product.hstatic.net/200000343865/product/37_675139be5fe944df8478df0302e802ab_master.jpg",
                    "Very good");

            Book b2 = new Book(2L,
                    "Sherlock Holmes",
                    60000L,
                    "Foreign",
                    "Conan Doyle",
                    "SHC",
                    "red",
                    "https://product.hstatic.net/200000343865/product/sherlockholmes_a71c7d8fa60c42bd89fb350b350e60a9_large.jpg",
                    "Excited");

            Book b3 = new Book(3L,
                    "Diamond Girls",
                    45000L,
                    "Knowledge",
                    "Phạm Trường",
                    "Kim Dong",
                    "red",
                    "https://product.hstatic.net/200000343865/product/nhung-co-gai-kim-cuong_bia_636e10ccf15040f4ac883310be9ec63a_compact.jpg",
                    "Very funny, grateful !!!");

            Book b4 = new Book(4L,
                    "Thien Ma",
                    65000L,
                    "Comic",
                    "Hà Thủy Nguyên",
                    "Kim Dong",
                    "blue",
                    "https://product.hstatic.net/200000343865/product/thien-ma_bia_f359027dd183434da5ef121c38bb8acb_master.jpg",
                    "Very good");

            Book b5 = new Book(5L,
                    "Diary of a Cricket",
                    40000L,
                    "VN",
                    "Tô Hoài",
                    "Kim Dong",
                    "orange",
                    "https://product.hstatic.net/200000343865/product/de-men_bia-thanh-chuong_2021-1_34478489fe664d17ae2f7b6f2dd8bdd7_9bcd611b7d8c4fe982c3a59f7f35024f_master.jpg",
                    "Beautiful World");

            Book b6 = new Book(6L,
                    "River Boy",
                    50000L,
                    "Foreign",
                    "Tim Bowler",
                    "Carnegie",
                    "blue",
                    "https://product.hstatic.net/200000343865/product/cau-be-dong-song_bia_5facaa597652478a93d87e01d87e4b9d_master.jpg",
                    "Very good");

            Book b7 = new Book(7L,
                    "Shin",
                    22000L,
                    "Manga",
                    "Yoshito Usui",
                    "Toei",
                    "white",
                    "https://product.hstatic.net/200000343865/product/22_5ce955b8c48c432ba69927df33136700_41684446f3c941baa5edddbfdfd4d6cd_master.jpg",
                    "Very good");

            Book b8 = new Book(8L,
                    "Detective Conan",
                    25000L,
                    "Manga",
                    "Gosho Aoyama",
                    "Toei",
                    "orange",
                    "https://product.hstatic.net/200000343865/product/68_e93edf1acccb4686bdf32f37f2d323ae_36caee7a096c466aaaa42d6e36a4e40c_large.jpg",
                    "Very good");
            bookService.save(List.of(b1,b2,b3,b4,b5,b6,b7,b8));
        };
    }
}
