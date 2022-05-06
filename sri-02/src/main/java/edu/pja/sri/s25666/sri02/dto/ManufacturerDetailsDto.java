package edu.pja.sri.s25666.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ManufacturerDetailsDto extends RepresentationModel<ManufacturerDetailsDto> {
    private Long id;
    private String name;
    private Set<MedicineDto> medicines;
}
