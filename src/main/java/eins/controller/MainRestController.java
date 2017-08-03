package eins.controller;

import eins.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainRestController {

    @Autowired
    private UserService uService;

    @PostMapping("/user/loginValidate")
    public boolean loginValidate(@RequestParam String userLogin){
        System.out.println(userLogin);
        return uService.loadUserByUsername(userLogin) == null;
    }

}
