package com.example.cosmetic.controller;


import com.example.cosmetic.dto.IImageDto;
import com.example.cosmetic.dto.IMainDto;
import com.example.cosmetic.model.product.Product;
import com.example.cosmetic.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/detail")
    public ResponseEntity<Product> productDetail(@RequestParam(name = "idProduct") Integer idProduct) {
        Product product = productService.findProductById(idProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/image")
    public ResponseEntity<List<IImageDto>> productImage(@RequestParam(name = "idProduct") Integer idProduct) {
        List<IImageDto> images = productService.findImage(idProduct);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
    @GetMapping("/sameType")
    public ResponseEntity<List<IMainDto>> productSameType(@RequestParam(name = "idProduct") Integer idProduct){
        List<IMainDto> products = productService.findProductByCategory(idProduct);
        return new ResponseEntity<>(products, HttpStatus.OK);    }
}
