package eins.controller;

import eins.entity.User;
import eins.service.interfaces.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){

        return "redirect:/main/index";
    }

    ////////////////////////////////////////////////////////////////////////////////////
}
