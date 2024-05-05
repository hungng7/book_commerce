package com.example.book_commerce.Service;

import com.example.book_commerce.Model.Order;
import com.example.book_commerce.Model.OrderDetail;
import com.example.book_commerce.Repository.OrderDetailRepository;
import com.example.book_commerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAll(){
        return orderDetailRepository.findAll();
    }
    public void save(OrderDetail od){
        orderDetailRepository.save(od);
    }
    public Optional<OrderDetail> getWithId(String id){
        return orderDetailRepository.findById(id);
    }
}
