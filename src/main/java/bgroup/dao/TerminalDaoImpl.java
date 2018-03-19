package bgroup.dao;

import bgroup.model.Terminal;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("terminalDao")
public class TerminalDaoImpl extends AbstractDao<Integer, Terminal> implements TerminalDao {

    static final Logger logger = LoggerFactory.getLogger(TerminalDaoImpl.class);

    @Override
    public List<Terminal> findByAzs(int azsNumber) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("azs", azsNumber));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Terminal> terminalList = (List<Terminal>) criteria.list();
        return terminalList;
    }

    @Override
    public boolean save(Terminal terminal) {
        try {
            persist(terminal);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Terminal> findAllLast() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.or(Restrictions.eq("locked", 0), Restrictions.isNull("locked")));
        //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Terminal> terminalList = (List<Terminal>) criteria.list();
        return terminalList;
    }

    @Override
    public Terminal findById(int id) {
        Terminal terminal = getByKey(id);
        return terminal;
    }


}
