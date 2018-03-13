package bgroup.service;

import bgroup.model.Org;
import bgroup.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface OrgService {

    Org findById(int id);

    //int saveOrg(HttpServletRequest request, User user);

    //List<Org> findAllByUserId(int id);
    List<Org> findAll();

}