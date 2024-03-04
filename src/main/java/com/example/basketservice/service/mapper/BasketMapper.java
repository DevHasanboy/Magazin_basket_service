package com.example.basketservice.service.mapper;

import com.example.basketservice.dto.BasketDto;
import com.example.basketservice.model.Basket;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class BasketMapper {
    @Mapping(target = "basketId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Basket toEntity(BasketDto dto);

    public abstract BasketDto toDto(Basket basket);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateFromDto(@MappingTarget Basket basket, BasketDto dto);
}
