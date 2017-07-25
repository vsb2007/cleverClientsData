package bgroup.service;

import bgroup.model.CleverCard;
import bgroup.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface CleverCardService {

    CleverCard findById(int id);

    CleverCard findByCardNumber(int cardNumber);

    int saveCleverCard(HttpServletRequest request, User user);

    List<CleverCard> findAllByAzsId(int id);

    List<CleverCard> findAllByUserId(int id);

    boolean isCleverCardNumberUnique(int number);
}