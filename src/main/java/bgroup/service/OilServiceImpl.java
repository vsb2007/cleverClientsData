package bgroup.service;

import bgroup.dao.OilDao;
import bgroup.model.Oil;
import bgroup.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("oilService")
@Transactional("transactionManager")
public class OilServiceImpl implements OilService {
    static final Logger logger = LoggerFactory.getLogger(OilServiceImpl.class);
    @Autowired
    private OilDao oilDao;

    @Override
    public Oil findById(int id) {
        return oilDao.findById(id);
    }

    @Override
    public List<Oil> findAll() {
        return oilDao.findAll();
    }
}
