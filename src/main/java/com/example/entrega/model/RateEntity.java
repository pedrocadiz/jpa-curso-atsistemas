package com.example.entrega.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(mappedBy = "rateEntities" ,fetch = FetchType.LAZY)
    private List<CarEntity> carEntities;
}
