package bgroup.service;

import bgroup.model.Snow;
import bgroup.model.Terminal;
import bgroup.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SnowService {

    Snow findById(int id);

    List<Snow> findByAzs(int azsNumber);
    int createSnow(HttpServletRequest request, User user);
    int updateSnow(HttpServletRequest request, User user);
    List<Snow> findAllLast(User user);
    int lockSnow(Snow snow, User user);

}