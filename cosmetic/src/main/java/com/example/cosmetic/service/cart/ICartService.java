package com.example.cosmetic.service.cart;

import com.example.cosmetic.dto.ICartDto;
import com.example.cosmetic.model.order.Cart;

import java.util.List;

public interface ICartService {
    void addToCart(Cart cart);

    List<ICartDto> findByIdUser(Integer idUser);

    Cart findByIdUserAndIdProduct(Integer idUser, Integer idProduct);

    void addQuantity(Integer idUser, Integer idProduct);

    void deleteProduct(Integer idUser, Integer idProduct);

    void increaseQuantity(Integer idUser, Integer idProduct);

    void decreaseQuantity(Integer idUser, Integer idProduct);

    Integer sumCart(String idUser);
}
