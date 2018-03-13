package bgroup.dao;

import bgroup.model.Org;

import java.util.List;


public interface OrgDao {

    Org findById(int id);

    boolean save(Org org);

    //void deleteByUserName(String userName);
    List<Org> findAll();

}

