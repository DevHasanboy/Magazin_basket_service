package com.example.basketservice.service;

import com.example.basketservice.dto.BasketDto;
import com.example.basketservice.dto.ResponseDto;
import com.example.basketservice.dto.SimpleCrud;
import com.example.basketservice.model.Basket;
import com.example.basketservice.repository.BasketRepository;
import com.example.basketservice.service.mapper.BasketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService implements SimpleCrud<Integer, BasketDto> {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    @Override
    public ResponseDto<BasketDto> create(BasketDto dto) {
        try {
            Basket basket = this.basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            this.basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.basketMapper.toDto(basket))
                    .build();
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-1)
                    .message(String.format("while is error %s :: saving",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<BasketDto> get(Integer entityId) {
        return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                .map(basket -> {
                    return ResponseDto.<BasketDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.basketMapper.toDto(basket))
                            .build();
                }).orElse(ResponseDto.<BasketDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",entityId))
                        .build());
    }

    @Override
    public ResponseDto<BasketDto> update(BasketDto dto, Integer entityId) {
        try {
            return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                    .map(basket -> {
                        basket.setUpdatedAt(LocalDateTime.now());
                        this.basketMapper.toUpdateFromDto(basket,dto);
                        this.basketRepository.save(basket);
                        return ResponseDto.<BasketDto>builder()
                                .success(true)
                                .message("ok")
                                .data(this.basketMapper.toDto(basket))

                                .build();


                    }).orElse(ResponseDto.<BasketDto>builder()
                            .code(-1)
                            .message(String.format("not found of %d :: id",entityId))
                            .build());
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-1)
                    .message(String.format("While is saving %s :: error",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<BasketDto> delete(Integer entityId) {
        return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                .map(basket -> {
                    basket.setDeletedAt(LocalDateTime.now());
                   return ResponseDto.<BasketDto>builder()
                            .success(true)
                            .message("ok")
                            .data(this.basketMapper.toDto(basket))
                            .build();
                }).orElse(ResponseDto.<BasketDto>builder()
                        .code(-1)
                        .message(String.format("not found of %d :: id",entityId))
                        .build());
    }

    public ResponseDto<List<BasketDto>> getAllBasket() {
        List<Basket> list=this.basketRepository.findAllBy();
        if (list.isEmpty()){
            return ResponseDto.<List<BasketDto>>builder()
                    .code(-1)
                    .message("empty")
                    .build();
        }
        return ResponseDto.<List<BasketDto>>builder()
                .success(true)
                .message("ok")
                .data(list.stream().map(this.basketMapper::toDto).toList())
                .build();
    }
}
