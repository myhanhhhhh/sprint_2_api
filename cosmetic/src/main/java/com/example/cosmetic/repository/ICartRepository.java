package com.example.cosmetic.repository;

import com.example.cosmetic.dto.ICartDto;
import com.example.cosmetic.model.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "SELECT " +
            " c.id_user AS idUser, " +
            " c.id_product AS idProduct, " +
            " c.quantity_of_order AS quantity, " +
            " p.quantity AS maxQuantity, " +
            " p.brand,  " +
            " p.name, " +
            " p.price, " +
            " MIN(i.name) AS image " +
            " FROM cart AS c " +
            " JOIN product AS p ON p.id = c.id_product " +
            " JOIN image AS i ON p.id = i.id_product " +
            " WHERE c.id_user = :id " +
            " GROUP BY c.id_user, c.id_product, c.quantity_of_order, p.name, p.price ", nativeQuery = true)
    List<ICartDto> getAllCart(@Param("id") Integer idUser);

    @Query(value = " SELECT * FROM cart " +
            " WHERE id_product = :idProduct AND id_user = :idUser ", nativeQuery = true)
    Cart findCart(@Param("idUser") Integer idUser, @Param("idProduct") Integer idProduct);

    @Query(value = " SELECT COUNT(cart.id_user) AS sum_cart " +
            " FROM cart " +
            " JOIN app_user ON cart.id_user = app_user.id  " +
            " WHERE app_user.user_name = :idUser GROUP BY cart.id_user ", nativeQuery = true)
    Integer sumCart(String idUser);

    @Transactional
    @Modifying
    @Query(value = " UPDATE cart JOIN product ON cart.id_product = product.id SET quantity_of_order = quantity_of_order + 1 " +
            " WHERE id_product = :idProduct AND id_user = :idUser AND quantity_of_order < quantity ", nativeQuery = true)
    void addQuantity(@Param("idUser") Integer idUser, @Param("idProduct") Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = " DELETE FROM cart WHERE id_user = :idUser AND id_product = :idProduct ", nativeQuery = true)
    void deleteCart(@Param("idUser") Integer idUser, @Param("idProduct") Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = " UPDATE cart join product ON cart.id_product = product.id SET quantity_of_order = quantity_of_order + 1 " +
            " WHERE id_product = :idProduct AND id_user = :idUser AND quantity_of_order < quantity ", nativeQuery = true)
    void increaseQuantity(@Param("idUser") Integer idUser, @Param("idProduct") Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = " UPDATE cart SET quantity_of_order = quantity_of_order - 1 " +
            " WHERE id_product = :idProduct AND id_user = :idUser AND quantity_of_order >= 1 ", nativeQuery = true)
    void decreaseQuantity(@Param("idUser") Integer cv, @Param("idProduct") Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = " DELETE FROM cart WHERE id_user = :idUser ", nativeQuery = true)
    void deleteCartByIdUser(@Param("idUser") Integer idUser);

}
