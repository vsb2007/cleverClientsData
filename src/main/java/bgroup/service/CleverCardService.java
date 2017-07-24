package bgroup.service;

import bgroup.model.CleverCard;
import bgroup.model.User;

import java.util.List;


public interface CleverCardService {

    CleverCard findById(int id);


    void saveCleverCard(CleverCard cleverCard);

    List<CleverCard> findAllByAzsId(int id);

    List<CleverCard> findAllByUserId(int id);
}