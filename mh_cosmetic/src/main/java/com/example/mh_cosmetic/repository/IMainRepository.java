package com.example.mh_cosmetic.repository;

import com.example.mh_cosmetic.dto.IMainDto;
import com.example.mh_cosmetic.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface IMainRepository extends JpaRepository<Product,Integer> {
    @Query(value = " SELECT " +
            "  p.id AS idProduct, " +
            "  p.name AS nameProduct, " +
            "  p.price AS priceProduct, " +
            "  c.name AS nameCategory, " +
            "  (SELECT i.name FROM image i WHERE i.id_product = p.id ORDER BY i.id LIMIT 1) AS firstImage " +
            "  FROM " +
            "  product p " +
            "  JOIN " +
            "  category c ON p.id_category = c.id LIMIT 8 ", nativeQuery = true)
    List<IMainDto> getOutstandingProduct();

    @Query(value = " SELECT " +
            "    p.id AS idProduct, " +
            "    p.name AS nameProduct, " +
            "    p.price AS priceProduct, " +
            "    c.name AS nameCategory, " +
            "    MAX(i.name) AS firstImage " +
            "    FROM product p " +
            "    JOIN category c ON p.id_category = c.id " +
            "    JOIN image i ON p.id = i.id_product " +
            "    WHERE p.name LIKE :valueSearchName " +
            "    AND c.id LIKE :idCategory " +
            "    GROUP BY p.id ", nativeQuery = true)
    Page<IMainDto> getAllProductByCategory(Pageable pageable, @Param("valueSearchName") String valueSearchName, @Param("idCategory") String idCategory);


}
