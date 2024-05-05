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
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public List<UserAccount> getAll(){
        return userAccountRepository.findAll();
    }
    public void save(UserAccount u){
        userAccountRepository.save(u);
    }
    public UserAccount getAccount(UserAccount account){
        Optional<UserAccount> found = userAccountRepository.findById(account.getUsername());
        return found.isPresent() && account.getPassword().equals(found.get().getPassword()) ?
                account : null;
    }
}
