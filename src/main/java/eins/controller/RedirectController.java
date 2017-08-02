package eins.controller;

import eins.entity.User;
import eins.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/main")
public class RedirectController {

    @GetMapping("/index")
    public String index(){ return "index"; }

    ///////////////////////////////////////////////////////////////////



    @Autowired
    private UserService uService;



    ///////////////////////////////////////////////////////////////////



    @ModelAttribute("loggedUser") public User loggedUser() { return new User(); }

    @ModelAttribute("passrecUser") public User passrecUser() { return new User(); }

    @ModelAttribute("regUser") public User regUser() { return new User(); }

}