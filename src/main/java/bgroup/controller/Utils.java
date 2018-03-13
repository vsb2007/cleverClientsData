package bgroup.controller;

import bgroup.model.User;
import bgroup.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by VSB on 20.02.2018.
 * cleverClients
 */
@Service("utils")
public class Utils {
    static final Logger logger = LoggerFactory.getLogger(Utils.class);

    @Autowired
    UserService userService;

    protected String getPrincipal() {
        String userName = null;
        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) return null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else if (principal instanceof User) {
            userName = ((User) principal).getUserName();
        } else {
            userName = principal.toString();
        }
        logger.debug("userName: {}", userName);
        return userName;
    }

    protected  User getUser() {
        User user = null;
        user = userService.findByUserName(getPrincipal());
        return user;
    }
}
