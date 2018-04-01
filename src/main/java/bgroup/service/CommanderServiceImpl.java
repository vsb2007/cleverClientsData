package bgroup.service;

import bgroup.dao.CommanderDao;
import bgroup.model.Commander;
import bgroup.model.Oil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commanderService")
@Transactional("transactionManager")
public class CommanderServiceImpl implements CommanderService {
    static final Logger logger = LoggerFactory.getLogger(CommanderServiceImpl.class);
    @Autowired
    private CommanderDao commanderDao;

    @Override
    public Commander findById(int id) {
        return commanderDao.findById(id);
    }

    @Override
    public List<Commander> findAll() {
        return commanderDao.findAll();
    }
}
