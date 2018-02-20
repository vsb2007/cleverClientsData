package bgroup.controller;

import bgroup.model.User;
import bgroup.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by VSB on 20.02.2018.
 * cleverClients
 */
@Controller
@SessionAttributes("roles")
public class TerminalsController {
    static final Logger logger = LoggerFactory.getLogger(TerminalsController.class);
    @Autowired
    Utils utils;

    @RequestMapping(value = {"terminals"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        User user = utils.getUser();

        model.addAttribute("loggedinuser", user);
        return "terminals";
    }



}
