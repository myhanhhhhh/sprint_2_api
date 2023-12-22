package com.example.cosmetic.service.cart;

import com.example.cosmetic.dto.ICartDto;
import com.example.cosmetic.model.order.Cart;
import com.example.cosmetic.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<ICartDto> findByIdUser(Integer idUser) {
        return cartRepository.getAllCart(idUser);
    }

    @Override
    public Cart findByIdUserAndIdProduct(Integer idUser, Integer idProduct) {
        return cartRepository.findCart(idUser, idProduct);
    }

    @Override
    public void addQuantity(Integer idUser, Integer idProduct) {
        cartRepository.addQuantity(idUser, idProduct);
    }

    @Override
    public void deleteProduct(Integer idUser, Integer idProduct) {
        cartRepository.deleteCart(idUser, idProduct);
    }

    @Override
    public void increaseQuantity(Integer idUser, Integer idProduct) {
        cartRepository.increaseQuantity(idUser, idProduct);
    }

    @Override
    public void decreaseQuantity(Integer idUser, Integer idProduct) {
        cartRepository.decreaseQuantity(idUser, idProduct);
        List<ICartDto> cartDto = cartRepository.getAllCart(idUser);
        for (ICartDto cart : cartDto) {
            if (cart.getQuantity() == 0) {
                cartRepository.deleteCart(idUser, idProduct);
            }
        }
    }

    @Override
    public Integer sumCart(String idUser) {
        return cartRepository.sumCart(idUser);
    }
}
