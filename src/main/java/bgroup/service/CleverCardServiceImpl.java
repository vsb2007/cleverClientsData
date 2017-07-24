package bgroup.service;

import bgroup.dao.CleverCardDao;
import bgroup.dao.UserDao;
import bgroup.model.CleverCard;
import bgroup.model.SmsSender;
import bgroup.model.User;
import bgroup.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service("cleverCardService")
@Transactional
public class CleverCardServiceImpl implements CleverCardService {

    @Autowired
    private CleverCardDao cleverCardDao;

    @Override
    public CleverCard findById(int id) {
        return cleverCardDao.findById(id);
    }

    @Override
    public void saveCleverCard(CleverCard cleverCard) {
        cleverCardDao.save(cleverCard);
    }

    @Override
    public List<CleverCard> findAllByAzsId(int id) {
        return cleverCardDao.findAllByAzsId(id);
    }

    @Override
    public List<CleverCard> findAllByUserId(int id) {
        return cleverCardDao.findAllByUserId(id);
    }
}
