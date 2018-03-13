package bgroup.service;

import bgroup.dao.OrgDao;
import bgroup.jsonService.JsonApiCleverCard;
import bgroup.model.CleverCard;
import bgroup.model.Org;
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

@Service("orgService")
@Transactional("transactionManager")
public class OrgServiceImpl implements OrgService {
    static final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
    @Autowired
    private OrgDao orgDao;

    @Override
    public Org findById(int id) {
        return orgDao.findById(id);
    }

    @Override
    public List<Org> findAll() {
        return orgDao.findAll();
    }
}
