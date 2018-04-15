package bgroup.controller;

import bgroup.model.User;
import bgroup.service.MoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VSB on 20.02.2018.
 * cleverClients
 */
@Controller
@EnableWebMvc
@SessionAttributes("roles")
public class MoneyController {
    static final Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @Autowired
    Utils utils;

    @Autowired
    MoneyService moneyService;

    @RequestMapping(value = {"money"}, method = RequestMethod.GET)
    public String money(ModelMap model) {

        User user = utils.getUser();
        return "money";
    }

    @RequestMapping(value = {"saveMoney"}, method = RequestMethod.POST)
    @ResponseBody
    public String createTerminal(UsernamePasswordAuthenticationToken principal, HttpServletRequest request) {

        User user = utils.getUser();
        if (user == null) return "нет авторизации";

        return moneyService.createMoney(request, user) + "";
    }

}
