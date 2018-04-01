package bgroup.service;

import bgroup.model.Commander;
import bgroup.model.Oil;

import java.util.List;


public interface CommanderService {

    Commander findById(int id);

    //int saveOrg(HttpServletRequest request, User user);

    List<Commander> findAll();

}