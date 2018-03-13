package bgroup.service;

import bgroup.model.Oil;
import bgroup.model.Org;

import java.util.List;


public interface OilService {

    Oil findById(int id);

    //int saveOrg(HttpServletRequest request, User user);

    List<Oil> findAll();

}