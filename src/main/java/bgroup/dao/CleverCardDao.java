package bgroup.dao;

import bgroup.model.CleverCard;
import bgroup.model.User;

import java.util.List;


public interface CleverCardDao {

    CleverCard findById(int id);

    void save(CleverCard cleverCard);

    //void deleteByUserName(String userName);
    List<CleverCard> findAllByAzsId(int id);
    List<CleverCard> findAllByUserId(int id);

}

