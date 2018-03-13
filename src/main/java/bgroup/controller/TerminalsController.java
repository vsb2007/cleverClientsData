package bgroup.controller;

import bgroup.model.Oil;
import bgroup.model.Org;
import bgroup.model.User;
import bgroup.service.OilService;
import bgroup.service.OrgService;
import bgroup.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by VSB on 20.02.2018.
 * cleverClients
 */
@Controller
@SessionAttributes("roles")
public class TerminalsController {
    static final Logger logger = LoggerFactory.getLogger(TerminalsController.class);

    @Autowired
    Utils utils;

    @Autowired
    OilService oilService;

    @Autowired
    OrgService orgService;

    @RequestMapping(value = {"terminals"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        User user = utils.getUser();
        List<Oil> oilList = oilService.findAll();
        List<Org> orgList = orgService.findAll();

        model.addAttribute("loggedinuser", user);
        model.addAttribute("oilList", oilList);
        model.addAttribute("orgList", orgList);
        //logger.debug("oil: {}", oilList.get(0).getOil());
        return "terminals";
    }


}
