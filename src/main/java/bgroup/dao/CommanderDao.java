package bgroup.dao;

import bgroup.model.Commander;
import bgroup.model.Oil;

import java.util.List;


public interface CommanderDao {

    Commander findById(int id);

    boolean save(Commander commander);

    //void deleteByUserName(String userName);
    List<Commander> findAll();

}

