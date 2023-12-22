package com.example.cosmetic.repository;

import com.example.cosmetic.dto.IOrderHistory;
import com.example.cosmetic.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = " SELECT * FROM customer WHERE id_user = :id ", nativeQuery = true)
    Customer findByIdUser(@Param("id") Integer idUser);

    @Query(value = " SELECT o.date_of_order AS dateOfOrder, p.name AS nameProduct, od.price_of_order AS priceOfOrder, od.quantity AS quantityOrder, o.time_of_order AS timeOfOrder " +
            " FROM customer c " +
            " JOIN orders o on c.id = o.id_user  " +
            " JOIN order_detail od ON o.id = od.id_order  " +
            " JOIN product p on od.id_product = p.id " +
            " WHERE  c.id_user = :id " +
            " AND o.payment_status = 1 " +
            " ORDER BY o.date_of_order DESC ", nativeQuery = true)
    List<IOrderHistory> findHistory(@Param("id") Integer idUser);
}
