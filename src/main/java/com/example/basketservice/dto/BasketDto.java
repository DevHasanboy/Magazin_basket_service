package com.example.basketservice.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {
    private Integer basketId;
    private Integer productId;
    private Double prodMass;
    private Double prodPrice;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
