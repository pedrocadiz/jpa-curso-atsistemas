package com.example.entrega.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private CarEntity carEntity;

    @ManyToOne
    private ClientEntity clientEntity;

    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
}