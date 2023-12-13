package com.example.cosmetic.controller;

import com.example.cosmetic.dto.ICartDto;
import com.example.cosmetic.model.order.Cart;
import com.example.cosmetic.model.product.Product;
import com.example.cosmetic.model.user.AppUser;
import com.example.cosmetic.service.cart.ICartService;
import com.example.cosmetic.service.product.IProductService;
import com.example.cosmetic.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IProductService productService;

    @GetMapping("/listCart")
    private ResponseEntity<List<ICartDto>> getAllCart(@RequestParam(name = "idUser") Integer idUser) {
        List<ICartDto> cartDtoList = cartService.findByIdUser(idUser);
        if (cartDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCart(@RequestParam(name = "idProduct") Integer idProduct,
                                            @RequestParam(name = "idUser") Integer idUser,
                                            @RequestParam(name = "quantity", defaultValue = "1", required = false) Integer quantity) {
        Cart cart = new Cart();
        Product product = productService.findById(idProduct);
        AppUser appUser = appUserService.findById(idUser);
        if (appUser == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cartProduct = cartService.findByIdUserAndIdProduct(idUser, idProduct);
        if (cartProduct != null) {
            cartService.addQuantity(idUser,idProduct);
            return new ResponseEntity<>(cartProduct,HttpStatus.OK);
        }
        cart.setProduct(product);
        cart.setAppUser(appUser);
        cart.setQuantityOfOrder(quantity);
        cartService.addToCart(cart);
        return  new ResponseEntity<>(cart,HttpStatus.OK);
    }


}
