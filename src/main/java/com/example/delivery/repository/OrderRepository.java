package com.example.delivery.repository;

import com.example.delivery.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Modifying
    @Transactional
    @Query(value = "update orders o set o.product_name = :productName where o.product_name = :condition",nativeQuery = true)
    void modifyAllProductName(@Param("productName")String rename,@Param("condition")String condition);
}
