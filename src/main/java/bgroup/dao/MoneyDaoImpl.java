package bgroup.dao;

import bgroup.model.Money;
import bgroup.model.Snow;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("moneyDao")
public class MoneyDaoImpl extends AbstractDao<Integer, Money> implements MoneyDao {

    static final Logger logger = LoggerFactory.getLogger(MoneyDaoImpl.class);

    @Override
    public List<Money> findByAzs(int azsNumber) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("azsId", azsNumber));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Money> snowList = (List<Money>) criteria.list();
        return snowList;
    }

    @Override
    public boolean save(Money money) {
        try {
            persist(money);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Money> findAllLast() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.or(Restrictions.eq("locked", 0), Restrictions.isNull("locked")));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Money> snowList = (List<Money>) criteria.list();
        return snowList;
    }

    @Override
    public Money findById(int id) {
        Money money = getByKey(id);
        return money;
    }


}
