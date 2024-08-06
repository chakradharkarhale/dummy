package com.example.Pegination.controller;

import com.example.Pegination.models.Product;
import com.example.Pegination.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer companyId,  //This indicates that the companyId parameter is optional.
            // The required attribute of @RequestParam specifies whether the parameter is mandatory or optional.
            @RequestParam(name = "offset") int offset,
            @RequestParam(name = "limit") int limit,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "id") String sortBy) {

        try {
            List<Product> products = productService.findProducts(companyId, offset, limit, sort, sortBy);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
