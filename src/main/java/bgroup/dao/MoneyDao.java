package bgroup.dao;

import bgroup.model.Money;
import bgroup.model.Snow;

import java.util.List;


public interface MoneyDao {
    Money findById(int id);
    List<Money> findByAzs(int azsNumber);
    boolean save(Money money);
    List<Money> findAllLast();
}

