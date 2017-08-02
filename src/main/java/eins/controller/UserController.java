package eins.controller;

import eins.entity.CompanyUser;
import eins.entity.User;
import eins.service.interfaces.DbService;
import eins.service.interfaces.MailService;
import eins.service.interfaces.UserService;
import eins.service.valid.UserLoginValidator;
import eins.service.valid.UserPassRecValidator;
import eins.service.valid.UserRegValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("error", "Не вірне ім'я та пароль.");
        }

        if (logout != null) {
            model.addAttribute("msg", "Для входу в систему введіть Ваш логін та пароль.");
        }

        return "loginPage";

    }

    @PostMapping(value = "/loginOk")
    public String loginOk(){
        return "redirect:/main/index";
    }

    @PostMapping(value = "/logout")
    public String logout(){
        return "redirect:/login?logout";
    }


    @GetMapping("/loginPage")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/registrationPage")
    public String registrationPage(Model model) {
        return "registrationPage";
    }







    @PostMapping("/regCompanyUser")
    public String regCompanyUser(@RequestParam String urOwnership,
                                 @RequestParam String urFullName,
                                 @RequestParam String urShortName,
                                 @RequestParam String urCode,
                                 @RequestParam String urName,
                                 @RequestParam String urSurname,
                                 @RequestParam String urUsername,
                                 @RequestParam String urEmail,
                                 @RequestParam String urPassword) {

        User user = new User();
        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setEmail(urEmail);

        CompanyUser cUser = new CompanyUser(0, urOwnership, urFullName, urShortName, urCode);

        uService.save(user, cUser);

        return "index";
    }



    @PostMapping("/regIndividualUser")
    public String regIndividualUser(@RequestParam String urName,
                                    @RequestParam String urSurname,
                                    @RequestParam String urUsername,
                                    @RequestParam String urEmail,
                                    @RequestParam String urPassword) {

        User user = new User();
        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setEmail(urEmail);
        uService.save(user);

        return "index";
    }

    @PostMapping("/passrecovery")
    public String passrecovery(@ModelAttribute("passrecUser") @Validated User user,
                               BindingResult result, Model model) {

        //System.out.println(user);
//        user = (User) user;

        /*if (result.hasErrors()) {
            model.addAttribute("passRecModDisplay", "block");
            return "index";
        }
        User fUser = uService.findByLogin(user.getLogin());

        if (fUser != null) {
            if (!uService.userTempPassIsValid(fUser)) {
                uService.setTempPassword(fUser.getId(), generateTempPass());
                fUser = uService.findOne(fUser.getId());
                System.out.println(fUser);
                System.out.println(fUser.getCreateTempPassword());
            }
            double min = (System.currentTimeMillis() - fUser.getCreateTempPassword().getTime())/60000;
            mailService.sendMailRecPass(fUser.getLogin(),fUser.getTempPassword(), (5.0-min));
        }*/
        return "index";
    }


    //////////////////////////////////////////////////////////////////////////////



    @Autowired
    private UserService uService;
    @Autowired
    private UserLoginValidator ulValidator;
    @Autowired
    private UserPassRecValidator uprValidator;
    @Autowired
    private UserRegValidator urValidator;
    @Autowired
    MailService mailService;
    @Autowired
    PasswordEncoder passwordEncoder;



    private String generateTempPass(){
        String pass = "";
        Random r = new Random();
        List<Supplier<Integer>> funcs = new ArrayList<>();
        // number char code [48 - 57]
        funcs.add(() -> {return (r.nextInt(10)+48);});
        // bigger = 65 - 90
        funcs.add(() -> {return (r.nextInt(26)+65);});
        // smaller = 97 - 122
        funcs.add(() -> {return (r.nextInt(26)+97);});
        for (int i = 0; i < 6; i++){
            char ch = (char) (int) funcs.get(r.nextInt(3)).get();
            pass += ch;
        }
        return pass;
    }
}