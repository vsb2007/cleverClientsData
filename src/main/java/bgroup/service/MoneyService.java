package bgroup.service;

import bgroup.model.Money;
import bgroup.model.Snow;
import bgroup.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MoneyService {

    Money findById(int id);
    List<Money> findByAzs(int azsNumber);
    int createMoney(HttpServletRequest request, User user);
    int updateMoney(HttpServletRequest request, User user);
    List<Money> findAllLast(User user);
    int lockMoney(Money money, User user);

}