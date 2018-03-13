package bgroup.dao;

import bgroup.model.Oil;
import bgroup.model.Org;

import java.util.List;


public interface OilDao {

    Oil findById(int id);

    boolean save(Oil oil);

    //void deleteByUserName(String userName);
    List<Oil> findAll();

}

