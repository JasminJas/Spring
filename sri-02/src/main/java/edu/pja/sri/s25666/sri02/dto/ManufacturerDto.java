package edu.pja.sri.s25666.sri02.dto;

import edu.pja.sri.s25666.sri02.model.Medicine;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDto extends RepresentationModel<ManufacturerDto> {
    private Long id;
    private String name;
}