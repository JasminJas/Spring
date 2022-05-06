package edu.pja.sri.s25666.sri02.dto;

import edu.pja.sri.s25666.sri02.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto extends RepresentationModel<MedicineDto> {
    private Long id;
    private String name;
    private String api;
    private Double dose;
    private LocalDate expirationDate;
    private boolean isWithoutPrescription;
    private Manufacturer manufacturer;
}