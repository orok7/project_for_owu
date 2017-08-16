package eins.controller;

import eins.entity.CompanyUser;
import eins.entity.Invoice;
import eins.entity.Review;
import eins.entity.User;
import eins.service.interfaces.InvoiceService;
import eins.service.interfaces.ReviewService;
import eins.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Controller
@RequestMapping("/user")
public class UserController {

    //TODO change password
    //TODO recovery password

    @PostMapping("/userPage/savePersonal")
    public String savePersonal(@RequestParam String urId,
                               @RequestParam(required = false) String urOwnership,
                               @RequestParam(required = false) String urFullName,
                               @RequestParam(required = false) String urShortName,
                               @RequestParam(required = false) String urCode,
                               @RequestParam String urName,
                               @RequestParam String urSurname,
                               @RequestParam String urPhoneNumber,
                               @RequestParam String urEmail){

        int id = 0;
        try { id = Integer.valueOf(urId); } catch (NumberFormatException e) { }
        User user = uService.findOneWithCompanyData(id);
        if (user == null) return "redirect:/user/userPage/main";

        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);

        if (!user.isCompany()) {
            uService.save(user);
            return "redirect:/user/userPage/main";
        }

        CompanyUser cUser = user.getCompanyDate();
        cUser.setOwnership(urOwnership);
        cUser.setFullName(urFullName);
        cUser.setShortName(urShortName);
        cUser.setCode(urCode);

        uService.save(user, cUser);

        return "redirect:/user/userPage/main";
    }

    @GetMapping("/userPage/personal")
    public String userPagePersonal(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","block");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upRatingShow","none");
        model.addAttribute("userPagePersonal","active");
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }

    @GetMapping({"/userPage/main", "/userPage/invoice"})
    public String userPageInvoice(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        User user = uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId());
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","block");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upRatingShow","none");
        model.addAttribute("userPageInvoice","active");
        model.addAttribute("listInvoice", invService.findAllByBuyerId(user.getId()));
        model.addAttribute("loggedUser", user);
        return "userPage";
    }

    @GetMapping("/userPage/review")
    public String userPageReview(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","block");
        model.addAttribute("upRatingShow","none");
        model.addAttribute("userPageReview","active");
        List<Review> reviews = reviewService.findAllByUserUsername(principal.getName());
        System.out.println(reviews);
        model.addAttribute("reviews", reviews);
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }

    @GetMapping("/userPage/rating")
    public String userPageRating(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upRatingShow","block");
        model.addAttribute("userPageRating","active");
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }


    @GetMapping("/userPage/detailsInvoice{id}")
    public String detailsInvoice(@PathVariable("id") int id,
                                 Model model) {
        Invoice invoice = invService.findOneWithProducts(id);
        model.addAttribute("invoiceProducts", invoice.getProducts());
        model.addAttribute("invoiceSum", invoice.getSum());
        return "userInvoiceDetails";
    }


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
    public String loginPage() {
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
                                 @RequestParam String urPhoneNumber,
                                 @RequestParam String urUsername,
                                 @RequestParam String urEmail,
                                 @RequestParam String urPassword) {

        User user = new User();

        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);

        CompanyUser cUser = new CompanyUser(0, urOwnership, urFullName, urShortName, urCode);

        uService.save(user, cUser);

        return "redirect:/user/login";
    }



    @PostMapping("/regIndividualUser")
    public String regIndividualUser(@RequestParam String urName,
                                    @RequestParam String urSurname,
                                    @RequestParam String urPhoneNumber,
                                    @RequestParam String urUsername,
                                    @RequestParam String urEmail,
                                    @RequestParam String urPassword) {

        User user = new User();
        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);
        uService.save(user);

        return "redirect:/user/login";
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
    private InvoiceService invService;
    @Autowired
    private ReviewService reviewService;
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