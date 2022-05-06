package edu.pja.sri.s25666.sri02.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String api;
    private Double dose;
    private LocalDate expirationDate;
    private boolean isWithoutPrescription;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "manufacturer") //klucz obcy
    @JsonManagedReference
    private Manufacturer manufacturer;


}
