package bgroup.dao;

import bgroup.model.Org;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("OrgDao")
public class OrgDaoImpl extends AbstractDao<Integer, Org> implements OrgDao {

    static final Logger logger = LoggerFactory.getLogger(OrgDaoImpl.class);

    @Override
    public List<Org> findAll() {
        Criteria criteria = createEntityCriteria();
        //criteria.add(Restrictions.eq("azs", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Org> orgsList = (List<Org>) criteria.list();
        return orgsList;
    }



    public Org findById(int id) {
        Org org = getByKey(id);
        return org;
    }

    @Override
    public boolean save(Org org) {

        try {

            persist(org);
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
