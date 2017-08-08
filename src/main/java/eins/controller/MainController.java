package eins.controller;

import eins.entity.Product;
import eins.entity.User;
import eins.service.interfaces.DbService;
import eins.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "redirect:/main/index";
    }

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        List<Product> listProd = new ArrayList<>();
        List<Integer> listNum = new ArrayList<>();
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("prodid_")) {
                System.out.println("Name = " + cookie.getName());
                System.out.println("Id = " + cookie.getName().split("prodid_")[1]);
                System.out.println("Num = " + cookie.getValue());
                int id = Integer.valueOf(cookie.getName().split("prodid_")[1]);
                int num = Integer.valueOf(cookie.getValue());
                listProd.add(pService.findOne(id));
                listNum.add(num);
            }
        }
        model.addAttribute("listProd", listProd);
        model.addAttribute("listNum", listNum);
        return "cart";
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private ProductService pService;
}
