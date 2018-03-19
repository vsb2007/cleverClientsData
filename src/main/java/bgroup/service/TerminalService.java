package bgroup.service;

import bgroup.model.CleverCard;
import bgroup.model.Terminal;
import bgroup.model.User;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface TerminalService {

    Terminal findById(int id);

    List<Terminal> findByAzs(int azsNumber);
    int createTerminal(HttpServletRequest request,User user);
    int updateTerminal(HttpServletRequest request,User user);
    List<Terminal> findAllLast(User user);
    int lockTerminal(Terminal terminal,User user);

}