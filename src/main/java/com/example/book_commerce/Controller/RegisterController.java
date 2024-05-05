package com.example.book_commerce.Controller;

import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
public class RegisterController {
    UserAccountService userAccountService;
    @GetMapping("/register")
    public String RegisterPage(){
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserAccount req = new UserAccount(username,password);
        userAccountService.save(req);
        return "login";
    }
}
