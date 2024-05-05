package com.example.book_commerce.Controller;

import com.example.book_commerce.Model.UserAccount;
import com.example.book_commerce.Service.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Controller
public class LoginController {
    UserAccountService accountService;
    HttpSession httpSession;
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String executeLoginInfo(Model model,
                                   HttpServletRequest request,
                                   HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserAccount accountReq = new UserAccount(username,password);
        UserAccount account = accountService.getAccount(accountReq);
        if(account!=null){
            httpSession.setAttribute("account",account);
            return "redirect:/";
        }
        return "login";
    }
}
