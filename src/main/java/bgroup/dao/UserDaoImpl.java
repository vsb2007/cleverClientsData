package bgroup.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bgroup.model.User;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public User findByUserName(String userName) {
        logger.info("UserName : {}", userName);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deleted", false));
        criteria.addOrder(Order.asc("lastName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByUserName(String userName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User) crit.uniqueResult();
        delete(user);
    }

    @Override
    public User findByFio(String fio) {
        if (fio == null) return null;
        String[] fioArray = fio.split(" ");
        if (fioArray == null || fioArray.length != 3) return null;
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("lastName", fioArray[0]));
        crit.add(Restrictions.eq("firstName", fioArray[1]));
        crit.add(Restrictions.eq("patronymicName", fioArray[2]));

        User user = (User) crit.uniqueResult();
        return user;
    }
}
