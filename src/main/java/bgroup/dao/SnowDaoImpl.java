package bgroup.dao;

import bgroup.model.Snow;
import bgroup.model.Terminal;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("snowDao")
public class SnowDaoImpl extends AbstractDao<Integer, Snow> implements SnowDao {

    static final Logger logger = LoggerFactory.getLogger(SnowDaoImpl.class);

    @Override
    public List<Snow> findByAzs(int azsNumber) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("azsId", azsNumber));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Snow> snowList = (List<Snow>) criteria.list();
        return snowList;
    }

    @Override
    public boolean save(Snow snow) {
        try {
            persist(snow);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Snow> findAllLast() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.or(Restrictions.eq("locked", 0), Restrictions.isNull("locked")));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Snow> snowList = (List<Snow>) criteria.list();
        return snowList;
    }

    @Override
    public Snow findById(int id) {
        Snow snow = getByKey(id);
        return snow;
    }


}
