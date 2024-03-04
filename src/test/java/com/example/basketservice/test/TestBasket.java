package com.example.basketservice.test;

import com.example.basketservice.dto.BasketDto;
import com.example.basketservice.dto.ResponseDto;
import com.example.basketservice.model.Basket;
import com.example.basketservice.repository.BasketRepository;
import com.example.basketservice.service.BasketService;
import com.example.basketservice.service.mapper.BasketMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestBasket {

    private BasketMapper basketMapper;
    private BasketService basketService;
    private BasketRepository basketRepository;


    @BeforeEach
    void init(){
        this.basketMapper= Mockito.mock(BasketMapper.class);
        this.basketRepository=Mockito.mock(BasketRepository.class);
        this.basketService=new BasketService(basketRepository,basketMapper);
    }

    @Test
    void createPositive(){
        when(this.basketMapper.toEntity(any()))
                .thenReturn(Basket.builder()
                        .basketId(1)
                        .prodMass(23.4)
                        .build());

        when(this.basketMapper.toDto(any()))
                .thenReturn(BasketDto.builder()
                        .basketId(4)
                        .productId(5)
                        .build());

        ResponseDto<BasketDto> response=this.basketService.create(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getBasketId(),4);
        Assertions.assertEquals(response.getData().getProductId(),5);

        verify(this.basketMapper,times(1)).toEntity(any());
        verify(this.basketMapper,times(1)).toDto(any());
    }

    @Test
    void createNegativeException(){
        when(this.basketMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<BasketDto> response=this.basketService.create(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void getPositive(){
        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Basket.builder()
                                .basketId(1)
                                .prodMass(76.0)
                                .build()));


        when(this.basketMapper.toDto(any()))
                .thenReturn(BasketDto.builder()
                        .basketId(4)
                        .productId(5)
                        .build());

        ResponseDto<BasketDto> response=this.basketService.get(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getBasketId(),4);
        Assertions.assertEquals(response.getData().getProductId(),5);

        verify(this.basketRepository,times(1)).findByBasketIdAndDeletedAtIsNull( any());
        verify(this.basketMapper,times(1)).toDto(any());
    }

    @Test
    void getNegative(){
        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<BasketDto> response=this.basketService.get(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    void updatePositive(){
        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Basket.builder()
                        .basketId(1)
                        .prodMass(76.0)
                        .build()));


        when(this.basketMapper.toDto(any()))
                .thenReturn(BasketDto.builder()
                        .basketId(4)
                        .productId(5)
                        .build());

        ResponseDto<BasketDto> response=this.basketService.update(new BasketDto(),any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getBasketId(),4);
        Assertions.assertEquals(response.getData().getProductId(),5);

        verify(this.basketRepository,times(1)).findByBasketIdAndDeletedAtIsNull( any());
        verify(this.basketMapper,times(1)).toDto(any());
    }
    @Test
    void updateNegative(){

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<BasketDto> response=this.basketService.update(new BasketDto(),any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
    }
    @Test
    void deletePositive(){
        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Basket.builder()
                        .basketId(1)
                        .prodMass(76.0)
                        .build()));


        when(this.basketMapper.toDto(any()))
                .thenReturn(BasketDto.builder()
                        .basketId(4)
                        .productId(5)
                        .build());

        ResponseDto<BasketDto> response=this.basketService.delete(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getBasketId(),4);
        Assertions.assertEquals(response.getData().getProductId(),5);

        verify(this.basketRepository,times(1)).findByBasketIdAndDeletedAtIsNull( any());
        verify(this.basketMapper,times(1)).toDto(any());
    }
    @Test
    void deleteNegative(){
        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());
        ResponseDto<BasketDto> response=this.basketService.delete(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
    }
}
