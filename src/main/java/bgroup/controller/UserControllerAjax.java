package bgroup.controller;

import bgroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VSB on 10.02.2017.
 * MiS
 */
@Controller
@EnableWebMvc
public class UserControllerAjax {

    @Autowired
    UserService userService;

    @RequestMapping(value = "updatePassword", produces = {"text/plain; charset=UTF-8"})
    @ResponseBody
    public String updatePassword(UsernamePasswordAuthenticationToken principal, HttpServletRequest request) {
        String responseBody = "Error";
        if (userService.changePassword(request.getParameter("id"))) {
            return "Пароль поменяли";
        }

        return "Ошибка смены пароля";
    }

}
