package com.example.book_commerce.API;

import com.example.book_commerce.DTO.UserAccountDTO;
import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountAPI {
    @Autowired
    private UserAccountService userService;

    @PostMapping(path ="/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccountDTO userDTO){
        UserAccount u = UserAccount.builder()
                .username(userDTO.getUsername())
                .password( userDTO.getPassword())
                .build();
        userService.save(u);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccount> login(@RequestBody UserAccountDTO userDTO,
                                             HttpSession session){

        UserAccount accountReq = UserAccount.builder()
                .username(userDTO.getUsername())
                .password( userDTO.getPassword())
                .build();
        UserAccount account = userService.getAccount(accountReq);
        if(account!=null){
            session.setAttribute("account",account);
            return new ResponseEntity<>(accountReq, HttpStatus.OK);
        }
        return new ResponseEntity<>(accountReq, HttpStatus.BAD_REQUEST);
    }
}
