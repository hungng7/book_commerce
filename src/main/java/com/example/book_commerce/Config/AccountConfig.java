package com.example.book_commerce.Config;

import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.UserAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner accountcommandLineRunner(UserAccountService userAccountService){
        return args -> {
            UserAccount u1 = new UserAccount("user1","123");
            userAccountService.save(u1);
        };
    }
}
