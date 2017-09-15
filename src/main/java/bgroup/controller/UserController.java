package bgroup.controller;

import bgroup.model.CleverCard;
import bgroup.model.User;
import bgroup.model.UserProfile;
import bgroup.service.CleverCardService;
import bgroup.service.CustomUserDetailsService;
import bgroup.service.UserProfileService;
import bgroup.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    CleverCardService cleverCardService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    /*@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    */
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String indexPage(ModelMap model) {
        User user = getUser();
        model.addAttribute("loggedinuser", user);
        return "index";
    }

    @RequestMapping(value = {"userslist"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        User user = getUser();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", user);
        return "userslist";
    }

    @RequestMapping(value = {"addccard"}, method = RequestMethod.GET)
    public String addccard(ModelMap model) {
        User user = getUser();
        if (user != null && user.isUserHasRole("LOGIN")) {
            //logger.debug("ok");
        } else {
            //logger.error("что-то не так!... закрыта сессия скорее всего!");
        }
        model.addAttribute("ccard", new CleverCard());
        model.addAttribute("user", user);
        model.addAttribute("loggedinuser", user);
        return "ccard";
    }

    @RequestMapping(value = "saveCcard")
    public String saveCcard(HttpServletRequest request, ModelMap model) {
        //logger.info("start: {}", request);
        User user = getUser();
        String error = null;
        int err = cleverCardService.saveCleverCard(request, user);
        if (err == 0) {
            error = "Форма успешно зарегистрирована";
        } else if (err == -3) {
            error = "Ошибка: номер карты зарегистрирован";
        } else if (err == -10) {
            error = "Проблема с БД";
        } else {
            error = "Заполните форму: ошибка " + err;
        }
        model.addAttribute("success", error);
        model.addAttribute("loggedinuser", user);
        return "ccard";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        User userLoggedIn = getUser();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", userLoggedIn);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
        if (!userService.isUserNameUnique(user.getId(), user.getUserName())) {
            FieldError ssoError = new FieldError("user", "userName", messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);
        User userLoggedIn = getUser();
        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", userLoggedIn);
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{userName}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String userName, ModelMap model) {
        User user = userService.findByUserName(userName);
        User userLoggedIn = getUser();
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", userLoggedIn);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{userName}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User userEdit, BindingResult result,
                             ModelMap model, @PathVariable String userName) {
        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserNameUnique(user.getId(), user.getUserName())){
			FieldError ssoError =new FieldError("user","userName",messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/
        User loggedinuser = getUser();
        userService.updateUser(userEdit);
        //logger.info("User: {}", userEdit);
        model.addAttribute("success", "User " + userEdit.getFirstName() + " " + userEdit.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", loggedinuser);
        model.addAttribute("edit", true);
        model.addAttribute("user", userEdit);
        return "registration";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-user-{userName}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String userName) {
        /*
        нужно добавить логер
		 */
        userService.deleteUserByUserName(userName);
        return "redirect:/index";
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            List<User> users = userService.findAllUsers();
            if (users != null) {
                model.addAttribute("usersList", users);
            }
            return "login";
        } else {
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/getContext", method = RequestMethod.POST)
    public String loginPageAuth(HttpServletRequest request) {
        //CustomUserDetailsService
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String fio = request.getParameter("fio");
        User user = userService.findByFio(fio);
        if (user == null) return "redirect:/login";
        UserProfile r = new UserProfile();
        r.setType("LOGIN");
        List<UserProfile> roles = new LinkedList<UserProfile>();
        roles.add(r);
        user.setUserProfiles(roles);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), userService.getGrantedAuthorities(user));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loginAdminPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "admin";
        } else {
            return "redirect:/index";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            //persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else if (principal instanceof User) {
            userName = ((User) principal).getUserName();
        } else {
            userName = principal.toString();
        }
        logger.debug("userName: {}", userName);
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    private User getUser() {
        User user = null;
        user = userService.findByUserName(getPrincipal());
        return user;
    }

}