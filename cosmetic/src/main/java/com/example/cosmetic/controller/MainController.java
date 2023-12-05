package com.example.cosmetic.controller;

import com.example.cosmetic.dto.IMainDto;
import com.example.cosmetic.model.product.Category;
import com.example.cosmetic.service.main.ICategoryService;
import com.example.cosmetic.service.main.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class MainController {
    @Autowired
    private IMainService mainService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/main")
    public ResponseEntity<List<IMainDto>> getOutstandingProduct() {
        List<IMainDto> listProduct = mainService.getListProduct();
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<IMainDto>> getAllProduct(@RequestParam(name = "_limit", defaultValue = "12", required = false) int limit,
                                                        @RequestParam(name = "_page", defaultValue = "0", required = false) int page,
                                                        @RequestParam(name = "name_like", defaultValue = "", required = false) String searchName,
                                                        @RequestParam(name = "category", defaultValue = "", required = false) String category,
                                                        @RequestParam(name = "sort", defaultValue = "", required = false) String sortPrice) {
        Pageable pageable = PageRequest.of(page, limit);
        if (sortPrice.equals("1")) {
            pageable = PageRequest.of(page, limit, Sort.by("priceProduct").ascending());
        } else if (sortPrice.equals("2")) {
            pageable = PageRequest.of(page, limit, Sort.by("priceProduct").descending());
        }
        Page<IMainDto> mainDtoPage = mainService.findAllProductByName(pageable, searchName, category);
        if (mainDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mainDtoPage, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categoryList = categoryService.getAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
