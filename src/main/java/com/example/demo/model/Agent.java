package com.example.demo.model;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "agents")
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int seniority;
    @Column
    private LocalDate hireDate = getHireDate();
    @Column
    private double saleValue;
    @Column
    private double baseSalary;
    @Column
    private double saleCommission = getSaleCommission();
    @Column
    private double totalSalary = getTotalSalary();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Estate> estates;

    public Agent(String name,
                 String surname,
                 int seniority,
                 double baseSalary,
                 Estate... estate) {
        this.name = name;
        this.surname = surname;
        this.seniority = seniority;
        this.baseSalary = baseSalary;
        estates = new ArrayList<>();
        Collections.addAll(estates, estate);
        setSaleValue(this.saleValue);
        setSaleCommission(this.saleCommission);
        setTotalSalary(this.totalSalary);
    }

    public Agent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }

    public LocalDate getHireDate() {
        return LocalDate.now().minusYears(seniority);
    }


    public double getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(double saleValue) {
        this.saleValue = getEstates().stream()
                .mapToDouble(estate -> estate.getPricePerSquareMeter() * estate.getArea())
                .sum();

    }

    public double getSaleCommission() {
        return saleCommission;
    }

    public void setSaleCommission(double saleCommission) {
        this.saleCommission = this.saleValue * 0.02;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = this.saleCommission + this.baseSalary;
    }
}
