package baith2.Controller;

import baith2.Product.Product;
import baith2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProductController {
    @GetMapping("/")
    public String showIndex(){
        return "index";
    }
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/products")
    public String getProduct(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("listProduct", productList);
        return "product";
    }
    
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(name = "id") String code, Model model){
        Product product= productRepository.findByCode(code);
        model.addAttribute("productEdit", product);
        return "edit";
    }
    @PostMapping(value = "/update")
    public String updateProduct(@ModelAttribute("productEdit") Product product){
        productRepository.save(product);
        System.out.println(product.toString());
        return "redirect:/products";
    }
    
    @GetMapping("/addProduct")
    public String showAddProduct(Model model){
        model.addAttribute("newProduct", new Product());
        return "addproduct";
    }
    @PostMapping(value = "/add")
    public String addProduct(@ModelAttribute("newProduct") Product product){
    	System.out.println(product.toString());
        productRepository.save(product);
        return "redirect:/products";
    }
    
    @GetMapping("/delete/{id}")
    public String findProduct(Model model, @PathVariable(name = "id") String code) {
        Product product = productRepository.findByCode(code);
        model.addAttribute("productDelete", product);
        model.addAttribute("productDeleteCode", code);
        return "delete";
    }
//    @PostMapping(value = "/delete")
//    public String deleteProduct(@ModelAttribute("productDeleteCode") String productCode) {
////    	System.out.println(product.toString());
//    	System.out.println("aaaa");
//        productRepository.deleteByCode(productCode);
//        return "redirect:/products";
//    }
    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestParam("code") String code) {
    	productRepository.deleteByCode(code);
    	return "redirect:/products";
    }
}
