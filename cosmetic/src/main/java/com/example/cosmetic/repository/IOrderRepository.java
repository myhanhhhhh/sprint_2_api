package com.example.cosmetic.repository;

import com.example.cosmetic.dto.IOrderDetailDto;
import com.example.cosmetic.model.order.Order;
import com.example.cosmetic.model.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = " SELECT * FROM orders " +
            " WHERE  id_user = :id and payment_status = 0" +
            " ORDER BY id DESC LIMIT 1 ",nativeQuery = true)
    Order findOrderById(@Param("id") Integer idUser);

    @Modifying
    @Transactional
    @Query(value = " INSERT INTO order_detail (price_of_order, quantity, id_order, id_product ) " +
            " VALUES(:#{#orderDetail.priceOfOrder}, :#{#orderDetail.quantity}, :#{#orderDetail.order.id}," +
            " :#{#orderDetail.product.id})", nativeQuery = true)
    Integer createOrderDetail(OrderDetail orderDetail);


    @Query(value = " SELECT price_of_order AS priceOfProduct, quantity AS quantity " +
            " FROM order_detail " +
            " WHERE id_order = :id",nativeQuery = true)
    List<IOrderDetailDto> findOrderDetailById(@Param("id") Integer orderId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders set payment_status = 1, total_money = :total WHERE id = :id", nativeQuery = true)
    void updateTotalMoney(@Param("total") double total,@Param("id") Integer id);


}
