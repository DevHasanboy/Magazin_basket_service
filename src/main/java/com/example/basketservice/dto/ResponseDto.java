package com.example.basketservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T>{
    private String message;
    private boolean success;
    private int code;
    private T data;
}
