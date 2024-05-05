package com.example.book_commerce.Repository;

import com.example.book_commerce.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
    UserAccount findByUsername(String name);
}