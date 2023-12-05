//package com.example.mh_cosmetic.repository;
//
//import com.example.mh_cosmetic.dto.IImageDto;
//import com.example.mh_cosmetic.dto.IMainDto;
//import com.example.mh_cosmetic.model.product.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.security.core.parameters.P;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//public interface IProductRepository extends JpaRepository<Product, Integer> {
//    @Query(value = " SELECT id, name  FROM image  WHERE id_product = :id ", nativeQuery = true)
//    List<IImageDto> findImage(@Param("id") Integer idProduct);
//
//    @Query(value = " SELECT product.id AS idProduct, " +
//            " product.name AS nameProduct, " +
//            " product.price AS priceProduct, " +
//            " category.name AS nameCategory, " +
//            " MAX(image.name) AS firstImage " +
//            " FROM product " +
//            " JOIN category ON product.id_category = category.id " +
//            " JOIN image ON product.id = image.id_product " +
//            " WHERE category.id = :id " +
//            " GROUP BY product.id " +
//            " LIMIT 4 ", nativeQuery = true)
//    List<IMainDto> findProductByCategory(@Param("id") Integer idCategory);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update product set quantity = :quantity where id = :id", nativeQuery = true)
//    void updateQuantityOfProduct(@Param("id") Integer id, @Param("quantity") Integer quantityOfProductAfterPayment);}
