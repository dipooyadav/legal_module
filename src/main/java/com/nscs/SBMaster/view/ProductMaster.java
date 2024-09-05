package com.nscs.SBMaster.view;

import com.nscs.SBMaster.Entity.BidStage;
import com.nscs.SBMaster.Entity.Product;
import com.nscs.SBMaster.repository.BidStageRepository;
import com.nscs.SBMaster.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductMaster {


    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/ProductIndex")
    public String showUserList(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute( "products",products);
        return "Product_All";
    }

    @GetMapping("/ProductSignup")
    public String showSignUpForm(@Valid Product product, BindingResult result, Model model) {
        model.addAttribute("products", product);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "Product_add";
        }
        return "Product_add";
    }


    @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Product_All";
        }

        productRepository.save(product);
        return "redirect:/ProductIndex";
    }



    @GetMapping("/editProductById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("product", product);

        return "Product_update";
    }

    @PostMapping("/updateProductByid/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "Product_update";
        }

        productRepository.save(product);

        return "redirect:/ProductIndex";
    }





}
