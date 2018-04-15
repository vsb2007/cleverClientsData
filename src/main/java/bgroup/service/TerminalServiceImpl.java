package bgroup.service;

import bgroup.dao.TerminalDao;
import bgroup.jsonService.JsonApiCleverCard;
import bgroup.model.CleverCard;
import bgroup.model.Terminal;
import bgroup.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("terminalService")
@Transactional("transactionManager")
public class TerminalServiceImpl implements TerminalService {
    static final Logger logger = LoggerFactory.getLogger(TerminalServiceImpl.class);
    @Autowired
    private TerminalDao terminalDao;

    @Override
    public Terminal findById(int id) {
        return terminalDao.findById(id);
    }

    @Override
    public List<Terminal> findByAzs(int azsNumber) {
        return terminalDao.findByAzs(azsNumber);
    }

    @Override
    public int createTerminal(HttpServletRequest request, User user) {
        return updateTerminal(request, user);
    }

    @Override
    public List<Terminal> findAllLast(User user) {
        return terminalDao.findAllLast();
    }

    @Override
    public int lockTerminal(Terminal terminal, User user) {
        terminal.setLocked(1);
        terminalDao.save(terminal);
        return 0;
    }

    @Override
    public int updateTerminal(HttpServletRequest request, User user) {
        logger.info("start: saveTerminal");
        String idString = request.getParameter("id");
        Terminal terminal = null;
        if (idString != null && !idString.equals("")) {
            int id = -1;
            try {
                id = Integer.parseInt(idString);
            } catch (Exception e) {
                return -7;
            }
            terminal = terminalDao.findById(id);
        }

        if (request == null) return -1;
        String dateString = request.getParameter("date");
        String smenaString = request.getParameter("smena");
        String orgString = request.getParameter("org");
        String vcoString = request.getParameter("vco");
        String vtermString = request.getParameter("vterm");
        String primString = request.getParameter("prim");
        String oilString = request.getParameter("oil");

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date dateWatch = null;
        try {
            java.util.Date dateUtil = df.parse(dateString);
            dateWatch = new java.sql.Date(dateUtil.getTime());
        } catch (Exception e) {
            logger.error(e.toString());
            return -5;
        }

        Integer smena = null;
        try {
            smena = Integer.parseInt(smenaString);
        } catch (Exception e) {
            return -1;
        }
        Integer org = null;
        try {
            org = Integer.parseInt(orgString);
        } catch (Exception e) {
            return -2;
        }
        Double vco = null;
        try {
            vco = Double.parseDouble(vcoString);
        } catch (Exception e) {
            return -3;
        }
        Double vterm = null;
        try {
            vterm = Double.parseDouble(vtermString);
        } catch (Exception e) {
            return -4;
        }
        Integer oil = null;
        try {
            oil = Integer.parseInt(oilString);
        } catch (Exception e) {
            return -5;
        }
        if (terminal == null || terminal.getId() == null) {
            terminal = new Terminal();
        }
        try {
            terminal.setDateWatch(dateWatch);
            terminal.setDateFill(new Date());
            terminal.setSmena(smena);
            terminal.setOilId(oil);
            terminal.setvCO(vco);
            terminal.setOrgId(org);
            terminal.setvTerm(vterm);
            terminal.setPrim(primString);
            terminal.setAzs(user.getAzs());
            terminal.setUserId(user.getId());
        } catch (Exception e) {
            logger.error("error: {}", e);
            return -6;
        }
        if (terminalDao.save(terminal)) return 0;
        return -10;
    }
}
