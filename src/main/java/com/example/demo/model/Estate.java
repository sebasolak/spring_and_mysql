package com.example.demo.model;

import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "estates")
@Setter
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column
    private Double pricePerSquareMeter;
    @Column
    private Double area;

    public Estate(PropertyType type,
                  Double pricePerSquareMeter,
                  Double area) {
        this.type = type.name();
        this.pricePerSquareMeter = pricePerSquareMeter;
        this.area = area;
    }

    public Estate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public void setPricePerSquareMeter(Double pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public Double getArea() {
        return area;
    }


    public void setArea(Double area) {
        this.area = area;
    }


}
