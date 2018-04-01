package bgroup.service;

import bgroup.dao.SnowDao;
import bgroup.model.Snow;
import bgroup.model.Terminal;
import bgroup.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("snowService")
@Transactional("transactionManager")
public class SnowServiceImpl implements SnowService {
    static final Logger logger = LoggerFactory.getLogger(SnowServiceImpl.class);
    @Autowired
    private SnowDao snowDao;

    @Override
    public Snow findById(int id) {
        return snowDao.findById(id);
    }

    @Override
    public List<Snow> findByAzs(int azsNumber) {
        return snowDao.findByAzs(azsNumber);
    }

    @Override
    public int createSnow(HttpServletRequest request, User user) {
        return updateSnow(request, user);
    }

    @Override
    public List<Snow> findAllLast(User user) {
        return snowDao.findAllLast();
    }

    @Override
    public int lockSnow(Snow snow, User user) {
        snow.setLocked(1);
        snowDao.save(snow);
        return 0;
    }

    @Override
    public int updateSnow(HttpServletRequest request, User user) {
        logger.info("start: saveSnow");
        String idString = request.getParameter("id");
        Snow snow = null;
        if (idString != null && !idString.equals("")) {
            int id = -1;
            try {
                id = Integer.parseInt(idString);
            } catch (Exception e) {
                return -7;
            }
            snow = snowDao.findById(id);
        }

        if (request == null) return -1;
        String dateString = request.getParameter("date");
        String targetString = request.getParameter("target");
        if (targetString == null || targetString.equals("")) return -8;
        String oilString = request.getParameter("oil");
        String vString = request.getParameter("v");
        String commanderString = request.getParameter("commander");
        String primString = request.getParameter("prim");

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date dateWatch = null;
        try {
            Date dateUtil = df.parse(dateString);
            dateWatch = new java.sql.Date(dateUtil.getTime());
        } catch (Exception e) {
            logger.error(e.toString());
            return -5;
        }
        Double v = null;
        try {
            v = Double.parseDouble(vString);
        } catch (Exception e) {
            return -3;
        }
        Integer oil = null;
        try {
            oil = Integer.parseInt(oilString);
        } catch (Exception e) {
            return -5;
        }
        Integer commander = null;
        try {
            commander = Integer.parseInt(commanderString);
        } catch (Exception e) {
            return -5;
        }
        if (snow == null || snow.getId() == null) {
            snow = new Snow();
        }
        try {
            snow.setDateWatch(dateWatch);
            snow.setDateFill(new Date());
            snow.setTarget(targetString);
            snow.setOilId(oil);
            snow.setV(v);
            snow.setCommanderId(commander);
            snow.setPrim(primString);
            snow.setAzsId(user.getAzs());
        } catch (Exception e) {
            logger.error("error: {}", e);
            return -6;
        }
        if (snowDao.save(snow)) return 0;
        return -10;
    }
}
