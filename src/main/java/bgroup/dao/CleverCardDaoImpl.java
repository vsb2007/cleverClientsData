package bgroup.dao;

import bgroup.model.CleverCard;
import bgroup.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("cleverCardDao")
public class CleverCardDaoImpl extends AbstractDao<Integer, CleverCard> implements CleverCardDao {

    static final Logger logger = LoggerFactory.getLogger(CleverCardDaoImpl.class);

    @Override
    public List<CleverCard> findAllByAzsId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("azs", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<CleverCard> cleverCards = (List<CleverCard>) criteria.list();
        return cleverCards;
    }

    @Override
    public List<CleverCard> findAllByUserId(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user_id", id));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<CleverCard> cleverCards = (List<CleverCard>) criteria.list();
        return cleverCards;
    }

    public CleverCard findById(int id) {
        CleverCard cleverCard = getByKey(id);
        return cleverCard;
    }

    @Override
    public void save(CleverCard cleverCard) {
        persist(cleverCard);
    }


}
