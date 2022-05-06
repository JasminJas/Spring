package edu.pja.sri.s25666.sri02.rest;

import edu.pja.sri.s25666.sri02.dto.MedicineDto;
import edu.pja.sri.s25666.sri02.dto.mapper.ManufacturerDtoMapper;
import edu.pja.sri.s25666.sri02.model.Medicine;
import edu.pja.sri.s25666.sri02.repo.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private MedicineRepository medicineRepository;
    private ModelMapper modelMapper;
    private ManufacturerDtoMapper manufacturerDtoMapper;

    public MedicineController(MedicineRepository medicineRepository, ModelMapper modelMapper){
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    private MedicineDto convertToDto(Medicine m){
        return modelMapper.map(m, MedicineDto.class);
    }

    private Medicine convertToEntity(MedicineDto dto){
        return modelMapper.map(dto, Medicine.class);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MedicineDto>> getMedicines(){
        List<Medicine> allMedicines = medicineRepository.findAll();
        List<MedicineDto> result = allMedicines.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        Link linkSelf = linkTo(methodOn(MedicineController.class).getMedicines()).withSelfRel();
        CollectionModel<MedicineDto> result2 = CollectionModel.of(result, linkSelf);
        return new ResponseEntity<>(result2, HttpStatus.OK);
    }

    @GetMapping("/{medId}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Long medId){
        Optional<Medicine> med = medicineRepository.findById(medId);
        if(med.isPresent()){
            MedicineDto medicineDto = convertToDto(med.get());
            return new ResponseEntity<>(medicineDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewMedicine(@RequestBody MedicineDto medDto){
        Medicine entity = convertToEntity(medDto);
        medicineRepository.save(entity);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{medId}")
    public ResponseEntity updateMedicine(@PathVariable Long medId, @RequestBody MedicineDto medicineDto){
        Optional<Medicine> currentMed = medicineRepository.findById(medId);
        if(currentMed.isPresent()){
            medicineDto.setId(medId);
            Medicine entity = convertToEntity(medicineDto);
            medicineRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{medId}")
    public ResponseEntity deleteMedicine(@PathVariable Long medId){
        medicineRepository.deleteById(medId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
