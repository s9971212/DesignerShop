package com.designershop.repositories;

import com.designershop.entities.CartItem;
import com.designershop.entities.OrderDelivery;
import com.designershop.entities.OrderItem;
import com.designershop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

    @Query(value = "SELECT * FROM order_deliveries WHERE is_default = 1 AND user_id =:userId", nativeQuery = true)
    OrderDelivery findByIsDefaultAndUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM order_deliveries WHERE user_id =:userId ORDER BY is_default DESC, delivery_id DESC", nativeQuery = true)
    List<OrderDelivery> findAllByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM order_deliveries WHERE delivery_id =:deliveryId", nativeQuery = true)
    OrderDelivery findByDeliveryId(@Param("deliveryId") String deliveryId);
}