package bgroup.dao;

import bgroup.model.Terminal;

import java.util.List;


public interface TerminalDao {
    Terminal findById(int id);
    List<Terminal> findByAzs(int azsNumber);
    boolean save(Terminal terminal);
    List<Terminal> findAllLast();
}

