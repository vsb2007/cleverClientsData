package bgroup.dao;

import bgroup.model.Snow;
import bgroup.model.Terminal;

import java.util.List;


public interface SnowDao {
    Snow findById(int id);
    List<Snow> findByAzs(int azsNumber);
    boolean save(Snow snow);
    List<Snow> findAllLast();
}

