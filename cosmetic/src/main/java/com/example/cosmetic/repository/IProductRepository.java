package com.example.cosmetic.repository;

import com.example.cosmetic.dto.IImageDto;
import com.example.cosmetic.dto.IMainDto;
import com.example.cosmetic.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update product set quantity = :quantity where id = :id", nativeQuery = true)
    void updateQuantityOfProduct(@Param("id") Integer id, @Param("quantity") Integer quantityOfProductAfterPayment);

    @Query(value = "SELECT id, name from image where id_product = :id", nativeQuery = true)
    List<IImageDto> findImage(@Param("id")Integer idProduct);

    @Query(value = " SELECT " +
            "    p.id AS idProduct, " +
            "    p.name AS nameProduct, " +
            "    p.price AS priceProduct, " +
            "    c.name AS nameCategory, " +
            "   MAX(i.name) AS firstImage " +
            " FROM product p " +
            " JOIN category c ON p.id_category = c.id " +
            " JOIN image i ON p.id = i.id_product " +
            " WHERE c.id = :id " +
            " GROUP BY p.id LIMIT 4 ", nativeQuery = true)
    List<IMainDto> findProductByCategory(@Param("id")Integer idCategory);
}
