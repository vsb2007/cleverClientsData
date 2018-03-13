package bgroup.dao;

import bgroup.model.Oil;
import bgroup.model.Org;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("OilDao")
public class OilDaoImpl extends AbstractDao<Integer, Oil> implements OilDao {

    static final Logger logger = LoggerFactory.getLogger(OilDaoImpl.class);

    @Override
    public List<Oil> findAll() {
        Criteria criteria = createEntityCriteria();
        //criteria.add(Restrictions.eq("azs", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Oil> oilList = (List<Oil>) criteria.list();
        return oilList;
    }



    public Oil findById(int id) {
        Oil oil = getByKey(id);
        return oil;
    }

    @Override
    public boolean save(Oil oil) {

        try {

            persist(oil);
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
