package bgroup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bgroup.model.SmsSender;
import bgroup.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bgroup.dao.UserDao;
import bgroup.model.User;

@Service("userService")
@Transactional("transactionManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //private SmsSender smsSender;

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByUserName(String userName) {
        User user = userDao.findByUserName(userName);
        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getDeleted() == null) user.setDeleted(false);
        if (user.getBlocked() == null) user.setBlocked(true);
        userDao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = userDao.findById(user.getId());
        if (entity != null) {
            entity.setUserName(user.getUserName());
            /*
            Пароль меняем другим образом
             */
           /*
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
             */
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setPatronymicName(user.getPatronymicName());
            entity.setPhone(user.getPhone());
            if (user.getBlocked() != null) {
                entity.setBlocked(user.getBlocked());
            } else {
                entity.setBlocked(false);
            }
            if (user.getDeleted() == null) {
                entity.setDeleted(false);
            }
            //entity.setDel(user.getDel());
            entity.setEmail(user.getEmail());
            entity.setAzs(user.getAzs());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }

    public void deleteUserByUserName(String userName) {
        User entity = userDao.findByUserName(userName);
        if (entity != null) {
            entity.setDeleted(true);
            entity = null;
        }
        //userDao.deleteByUserName(userName);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean isUserNameUnique(Integer id, String userName) {
        User user = findByUserName(userName);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    public boolean changePassword(String idString) {
        int id = -1;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {

        }
        User user = userDao.findById(id);
        if (user == null) return false;
        if (user.getPhone() == null) return false;
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int _$ = (int) (Math.random() * 9);
        for (int i = 0; i < 10; i++) {
            if (i == _$)
                sb.append('$');
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String newPassword = sb.toString();
        user.setPassword(passwordEncoder.encode(newPassword));
        //smsSender.sendSms(user.getPhone(), newPassword);
        return true;
    }

    public boolean isHasRole(String userName, String role) {
        User user = userDao.findByUserName(userName);
        if (user == null) return false;
        for (UserProfile userProfile : user.getUserProfiles()) {
            if (userProfile.getType().equals(role)) return true;
        }
        return false;
    }

    @Override
    public User findByFio(String fio) {
        return userDao.findByFio(fio);
    }

    public List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            //logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        //logger.info("authorities : {}", authorities);
        return authorities;
    }
}
