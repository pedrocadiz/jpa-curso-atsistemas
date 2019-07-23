package com.example.entrega.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String carPlate;
    private String registrationYear;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<RateEntity> rateEntities;


}
