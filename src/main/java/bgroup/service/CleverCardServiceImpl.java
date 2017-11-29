package bgroup.service;

import bgroup.dao.CleverCardDao;
import bgroup.model.CleverCard;
import bgroup.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("cleverCardService")
@Transactional("transactionManager")
public class CleverCardServiceImpl implements CleverCardService {
    static final Logger logger = LoggerFactory.getLogger(CleverCardServiceImpl.class);
    @Autowired
    private CleverCardDao cleverCardDao;

    @Override
    public CleverCard findById(int id) {
        return cleverCardDao.findById(id);
    }

    @Override
    public int saveCleverCard(HttpServletRequest request, User user) {
        logger.info("start: saveCleverCard");
        if (request == null) return -1;
        Integer cardNumber = null;
        String cardNumberString = request.getParameter("cardNumber");
        try {
            cardNumber = Integer.parseInt(cardNumberString);
        } catch (Exception e) {
            logger.error(e.toString());
            return -2;
        }
        CleverCard cleverCard = cleverCardDao.findByCardNumber(cardNumber);
        if (cleverCard == null) cleverCard = new CleverCard();
        else {
            return -3;
        }
        String f = request.getParameter("f");
        String i = request.getParameter("i");
        String o = request.getParameter("o");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String dateBirth = request.getParameter("dateBirth");
        String sexString = request.getParameter("sex");
        String vendorAuto = request.getParameter("vendorAuto");
        //String operatorNameCardOut = request.getParameter("operatorNameCardOut");
        //String operatorNameCardOut = request.getParameter(user.getFio());

        Integer sex = null;
        try {
            sex = Integer.parseInt(sexString);
        } catch (Exception e) {
            logger.error(e.toString());
            return -4;
        }

        cleverCard.setAzs(user.getAzs());
        cleverCard.setUserId(user.getId());
        cleverCard.setCardNumber(cardNumber);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate = null;
        try {
            java.util.Date birthDateUtil = df.parse(dateBirth);
            birthDate = new java.sql.Date(birthDateUtil.getTime());
        } catch (Exception e) {
            logger.error(e.toString());
            return -5;
        }
        logger.debug("id: " + cleverCard.getId());
        cleverCard.setDateBirth(birthDate);
        cleverCard.setDateFill(new Date());
        cleverCard.setEmail(email);
        cleverCard.setF(f);
        cleverCard.setI(i);
        cleverCard.setO(o);
        cleverCard.setPhoneNumber(phoneNumber);
        cleverCard.setSex(sex);
        cleverCard.setVendorAuto(vendorAuto);
        //cleverCard.setOperatorNameCardOut(operatorNameCardOut);
        if (cleverCardDao.save(cleverCard)) return 0;
        return -10;
    }

    @Override
    public List<CleverCard> findAllByAzsId(int id) {
        return cleverCardDao.findAllByAzsId(id);
    }

    @Override
    public List<CleverCard> findAllByUserId(int id) {
        return cleverCardDao.findAllByUserId(id);
    }

    @Override
    public CleverCard findByCardNumber(int cardNumber) {
        return cleverCardDao.findByCardNumber(cardNumber);
    }

    @Override
    public boolean isCleverCardNumberUnique(int number) {
        CleverCard cleverCard = findByCardNumber(number);
        if (cleverCard == null) return true;
        return false;
    }
}
