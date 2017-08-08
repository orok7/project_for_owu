package eins.controller;

import eins.entity.*;
import eins.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Manage Product table
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam String nameProduct,
                              @RequestParam String idProduct,
                              @RequestParam String productProductGroup,
                              @RequestParam String articleProduct,
                              @RequestParam String productMeasurementUnits,
                              @RequestParam String priceProduct,
                              @RequestParam String descriptionProduct,
                              @RequestParam MultipartFile picture,
                              Model model) throws IOException {
        int id = 0;
        try { id = Integer.valueOf(idProduct); } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: try to parse: \"" + idProduct + "\"");
        }

        Product product = (id==0)?new Product():pService.findOne(id);
        product.setName(nameProduct);
        product.setId(id);
        product.setProductGroup(pgService.findOne(Integer.valueOf(productProductGroup)));
        product.setArticle(articleProduct);
        product.setMeasurementUnits(MeasurementUnits.valueOf(productMeasurementUnits));
        product.setPrice(Double.valueOf(priceProduct));
        product.setDescription(descriptionProduct);

        String originalFilename = picture.getOriginalFilename();
        if (!originalFilename.isEmpty()) {
            String fileName = "" + (originalFilename + articleProduct).hashCode()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            if (id == 0 || !product.getMainPicture().equals("/imgdb/" + fileName)) {
                String realpath = System.getProperty("user.home") + File.separator + "images" + File.separator;
                picture.transferTo(new File(realpath + fileName));
                product.setMainPicture("/imgdb/" + fileName);
            }
        }

        try {
            pService.save(product);
        } catch (Exception e) {
            model.addAttribute("errorAdminPage", "Продукт з таким артикулом вже існує");
        }
        return "redirect:/admin/newProduct";
    }

    @GetMapping("/listProduct")
    public String listProduct(Model model){
        List<Product> list = pService.findAllWithGroups();
        list.sort( (o1, o2) -> o1.getName().compareTo(o2.getName()));
        model.addAttribute("productList", list);
        return "/productList";
    }

    @GetMapping("/newProduct")
    public String newProduct(@RequestParam(required = false) String errorAdminPage,
                             Model model){
        List<ProductGroup> productGroups = pgService.findAll();
        productGroups.sort( (o1, o2) -> o1.getName().compareTo(o2.getName()));
        if (errorAdminPage!=null)model.addAttribute("errorAdminPage", errorAdminPage);
        model.addAttribute("productGroups", productGroups);
        model.addAttribute("measurementUnits", MeasurementUnits.values());
        return "/newProduct";
    }

    @GetMapping("/modifyProduct{id}")
    public String modifyProduct(@PathVariable("id") int id, Model model){

        Product product = pService.findOneWithGroup(id);

        List<ProductGroup> productGroups = pgService.findAll();
        productGroups.sort( (o1, o2) -> o1.getName().compareTo(o2.getName()));
        model.addAttribute("productGroups", productGroups);
        model.addAttribute("productProductGroupId", product.getProductGroup().getId());
        model.addAttribute("measurementUnits", MeasurementUnits.values());
        model.addAttribute("measurementUnitSel", product.getMeasurementUnits());

        model.addAttribute("productId", id);
        model.addAttribute("productName", product.getName());
        model.addAttribute("productArticle", product.getArticle());
        model.addAttribute("productPrice", product.getPrice());
        model.addAttribute("productDescription", product.getDescription());
        model.addAttribute("productMainPicture", product.getMainPicture());
        return "/newProduct";
    }

    @GetMapping("/removeProduct{id}")
    public String removeProduct(@PathVariable("id") int id){
        pService.remove(id);
        return "redirect:/admin/listProduct";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Manage ProductGroup table
    //////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/saveProductGroup")
    public String saveProductGroup(@RequestParam String nameProductGroup,
                                   @RequestParam String idProductGroup,
                                   Model model){
        int id = 0;
        try { id = Integer.valueOf(idProductGroup); } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: try to parse: \"" + idProductGroup + "\"");
        }
        try {
            pgService.save(new ProductGroup(id, nameProductGroup));
        } catch (Exception e) {
            model.addAttribute("errorAdminPage","Група з такою назвою вже існує");
        }
        return "/newProductGroup";
    }

    @GetMapping("/listProductGroup")
    public String listProductGroup(Model model){
        List<ProductGroup> list = pgService.findAll();
        list.sort( (o1, o2) -> o1.getName().compareTo(o2.getName()));
        model.addAttribute("productGroupList", list);
        return "/productGroupList";
    }

    @GetMapping("/newProductGroup") public String newProductGroup(){ return "/newProductGroup"; }

    @GetMapping("/modifyProductGroup{id}")
    public String modifyProductGroup(@PathVariable("id") int id, Model model){
        model.addAttribute("productGroupId", id);
        model.addAttribute("productGroupName", pgService.findOne(id).getName());
        return "/newProductGroup";
    }

    @GetMapping("/removeProductGroup{id}")
    public String removeProductGroup(@PathVariable("id") int id){
        pgService.remove(id);
        return "redirect:/admin/listProductGroup";
    }


    ////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/adminPage") public String adminPage(){ return "adminPage"; }

    ////////////////////////////////////////////////////////////////////////



    @Autowired
    private UserService uService;

    @Autowired
    private ProductService pService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductGroupService pgService;

}
