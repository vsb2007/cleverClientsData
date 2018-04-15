package bgroup.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VSB on 15.04.2018.
 * cleverClients
 */
@Controller
@EnableWebMvc
public class ExceptionHandlingController {
    static final Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    //@ExceptionHandler(Exception.class)
    public ModelAndView handleNotFoundError(HttpServletRequest req, Exception ex) {
        logger.error("Request not found: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("login");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("login");
        return mav;
    }
}
