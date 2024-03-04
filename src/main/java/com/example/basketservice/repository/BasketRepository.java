package com.example.basketservice.repository;

import com.example.basketservice.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BasketRepository extends JpaRepository<Basket,Integer> {

    Optional<Basket> findByBasketIdAndDeletedAtIsNull(Integer basketId);


    List<Basket> findAllBy();
}
