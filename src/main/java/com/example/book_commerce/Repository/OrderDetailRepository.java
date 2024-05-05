package com.example.book_commerce.Repository;

import com.example.book_commerce.Model.Order;
import com.example.book_commerce.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}