package bgroup.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by VSB on 15.04.2018.
 * cleverClients
 */
@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {

        return "index";//this is view name
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex) {

        return "index";//this is view name
    }
}
