package com.druginventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "DrugTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "drugseq", sequenceName = "drug-sequence", allocationSize = 1)
    private Long drugId;

    @Column(name = "drug_name")
    @NotNull(message = "DrugName is required")
    private String drugName;

    private String manufacturer;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Quantity is required")
    private long quantity;

    @NotNull(message = "Price is required")
    private Double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    public Long getDrugId() {
//        return drugId;
//    }
//
//    public void setDrugId(Long drugId) {
//        this.drugId = drugId;
//    }
//
//    public String getDrugName() {
//        return drugName;
//    }
//
//    public void setDrugName(String drugName) {
//        this.drugName = drugName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(long quantity) {
//        this.quantity = quantity;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Drug() {
//
//    }
//
//    public Drug(Long drugId, String drugName, String manufacturer, String description, long quantity,
//                Double price, LocalDateTime createdAt,LocalDateTime updatedAt) {
//        this.drugId = drugId;
//        this.drugName = drugName;
//        this.manufacturer = manufacturer;
//        this.description = description;
//        this.quantity = quantity;
//        this.price = price;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
}
