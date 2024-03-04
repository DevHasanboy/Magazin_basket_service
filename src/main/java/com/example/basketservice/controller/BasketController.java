package com.example.basketservice.controller;

import com.example.basketservice.dto.BasketDto;
import com.example.basketservice.dto.ResponseDto;
import com.example.basketservice.dto.SimpleCrud;
import com.example.basketservice.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basket")
@RequiredArgsConstructor
public class BasketController implements SimpleCrud<Integer, BasketDto> {
    private final BasketService basketService;
    @PostMapping
    @Override
    @Operation(
            tags = "create"
    )
    public ResponseDto<BasketDto> create(@RequestBody @Valid BasketDto dto) {
        return this.basketService.create(dto);
    }

    @GetMapping("/{id}")
    @Override
    @Operation(
            tags = "get"
    )
    public ResponseDto<BasketDto> get(@PathVariable("id") Integer entityId) {
        return this.basketService.get(entityId);
    }

    @PutMapping("/{id}")
    @Override
    @Operation(
            tags = "update"
    )
    public ResponseDto<BasketDto> update(@RequestBody BasketDto dto, @PathVariable("id") Integer entityId) {
        return this.basketService.update(dto, entityId);
    }

    @DeleteMapping("/{id}")
    @Override
    @Operation(
            tags = "delete"
    )
    public ResponseDto<BasketDto> delete(@PathVariable("id") Integer entityId) {
        return this.basketService.delete(entityId);
    }
    @GetMapping("/get-all")
    @Operation(
            tags = "get"
    )
    public ResponseDto<List<BasketDto>> getAllBasket(){
        return this.basketService.getAllBasket();
    }
}
