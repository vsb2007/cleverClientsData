package bgroup.service;

import bgroup.dao.MoneyDao;
import bgroup.model.Money;
import bgroup.model.Snow;
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

@Service("moneyService")
@Transactional("transactionManager")
public class MoneyServiceImpl implements MoneyService {
    static final Logger logger = LoggerFactory.getLogger(MoneyServiceImpl.class);
    @Autowired
    private MoneyDao moneyDao;

    @Override
    public Money findById(int id) {
        return moneyDao.findById(id);
    }

    @Override
    public List<Money> findByAzs(int azsNumber) {
        return moneyDao.findByAzs(azsNumber);
    }

    @Override
    public int createMoney(HttpServletRequest request, User user) {
        return updateMoney(request, user);
    }

    @Override
    public List<Money> findAllLast(User user) {
        return moneyDao.findAllLast();
    }

    @Override
    public int lockMoney(Money money, User user) {
        money.setLocked(1);
        moneyDao.save(money);
        return 0;
    }

    @Override
    public int updateMoney(HttpServletRequest request, User user) {
        logger.info("start: saveMoney");
        String idString = request.getParameter("id");
        Money money = null;
        if (idString != null && !idString.equals("")) {
            int id = -1;
            try {
                id = Integer.parseInt(idString);
            } catch (Exception e) {
                return -7;
            }
            money = moneyDao.findById(id);
        }

        if (request == null) return -1;
        String dateString = request.getParameter("date");
        String vString = request.getParameter("v");
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

        if (money == null || money.getId() == null) {
            money = new Money();
        }
        try {
            money.setDateWatch(dateWatch);
            money.setDateFill(new Date());
            money.setV(v);
            money.setPrim(primString);
            money.setAzsId(user.getAzs());
            money.setUserId(user.getId());
        } catch (Exception e) {
            logger.error("error: {}", e);
            return -6;
        }
        if (moneyDao.save(money)) return 0;
        return -10;
    }
}
