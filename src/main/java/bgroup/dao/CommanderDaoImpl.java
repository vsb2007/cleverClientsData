package bgroup.dao;

import bgroup.model.Commander;
import bgroup.model.Oil;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("CommanderDao")
public class CommanderDaoImpl extends AbstractDao<Integer, Commander> implements CommanderDao {

    static final Logger logger = LoggerFactory.getLogger(CommanderDaoImpl.class);

    @Override
    public List<Commander> findAll() {
        Criteria criteria = createEntityCriteria();
        //criteria.add(Restrictions.eq("azs", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Commander> oilList = (List<Commander>) criteria.list();
        return oilList;
    }



    public Commander findById(int id) {
        Commander commander = getByKey(id);
        return commander;
    }

    @Override
    public boolean save(Commander commander) {

        try {

            persist(commander);
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
