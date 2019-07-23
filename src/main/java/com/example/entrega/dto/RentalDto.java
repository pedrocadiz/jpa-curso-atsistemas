package com.example.entrega.dto;


import lombok.Data;

@Data
public class RentalDto {

    private Integer id;
    private CarDto car;
    private ClientDto client;
    private Double price;
    private String startDate;
    private String endDate;

}
