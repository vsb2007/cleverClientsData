package bgroup.controller;

import bgroup.model.Commander;
import bgroup.model.Oil;
import bgroup.model.User;
import bgroup.service.CommanderService;
import bgroup.service.OilService;
import bgroup.service.SnowService;
import com.thoughtworks.qdox.model.expression.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by VSB on 20.02.2018.
 * cleverClients
 */
@Controller
@EnableWebMvc
@SessionAttributes("roles")
public class SnowController {
    static final Logger logger = LoggerFactory.getLogger(SnowController.class);

    @Autowired
    Utils utils;

    @Autowired
    OilService oilService;

    @Autowired
    CommanderService commanderService;

    @Autowired
    SnowService snowService;

    @RequestMapping(value = {"snow"}, method = RequestMethod.GET)
    public String listTerminals(ModelMap model) {

        User user = utils.getUser();
        List<Oil> oilList = oilService.findAll();
        List<Commander> commanderList = commanderService.findAll();

        model.addAttribute("loggedinuser", user);
        model.addAttribute("oilList", oilList);
        model.addAttribute("commanderList", commanderList);
        //logger.debug("oil: {}", oilList.get(0).getOil());
        return "snow";
    }

    @RequestMapping(value = {"createSnow"}, method = RequestMethod.POST)
    @ResponseBody
    public String createTerminal(UsernamePasswordAuthenticationToken principal, HttpServletRequest request) {

        User user = utils.getUser();
        if (user == null) return "нет авторизации";

        return snowService.createSnow(request, user) + "";
    }
}
