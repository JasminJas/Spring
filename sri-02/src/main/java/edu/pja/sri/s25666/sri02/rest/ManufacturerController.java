package edu.pja.sri.s25666.sri02.rest;

import edu.pja.sri.s25666.sri02.dto.ManufacturerDetailsDto;
import edu.pja.sri.s25666.sri02.dto.ManufacturerDto;
import edu.pja.sri.s25666.sri02.dto.MedicineDto;
import edu.pja.sri.s25666.sri02.dto.mapper.ManufacturerDtoMapper;
import edu.pja.sri.s25666.sri02.model.Manufacturer;
import edu.pja.sri.s25666.sri02.model.Medicine;
import edu.pja.sri.s25666.sri02.repo.ManufacturerRepository;
import edu.pja.sri.s25666.sri02.repo.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerRepository manufacturerRepository;
    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;
    private final ManufacturerDtoMapper manufacturerDtoMapper;

    private ManufacturerDto convertToDto(Manufacturer ma){return modelMapper.map(ma, ManufacturerDto.class);}
    private MedicineDto convertToDto(Medicine m){return modelMapper.map(m, MedicineDto.class);}
    private Manufacturer convertToEntity(ManufacturerDto dto){return modelMapper.map(dto, Manufacturer.class);}


    @GetMapping
    public ResponseEntity<Collection<ManufacturerDto>> getManufacturers() {
        List<Manufacturer> allManufacturerEntities = manufacturerRepository.findAll();
        List<ManufacturerDto> result = allManufacturerEntities.stream()
                .map(manufacturerDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{manufacturerId}")
    public ResponseEntity<ManufacturerDetailsDto> getManufacturerById(@PathVariable Long manufacturerId){
        Optional<Manufacturer> man = manufacturerRepository.findById(manufacturerId);
        if(man.isPresent()){
            ManufacturerDetailsDto manufacturerDto = manufacturerDtoMapper.convertToDetailsDto(man.get());
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
/*
    @GetMapping("/{manufacturerId}/products")
    public ResponseEntity<Collection<ProductDto>> getProductsOfManufacturerById(@PathVariable Long manufacturerId){
        List<Product> allProducts = productRepository.findProductsByManufacturerId(manufacturerId);
        List<ProductDto> result = allProducts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewManufacturer(@Valid @RequestBody ManufacturerDto man){
        Manufacturer entity = manufacturerDtoMapper.convertToEntity(man);
        manufacturerRepository.save(entity);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{manufacturerId}")
    public ResponseEntity updateManufacturer( @PathVariable Long manufacturerId, @Valid @RequestBody ManufacturerDto employeeDto) {
        Optional<Manufacturer> currentEmp = manufacturerRepository.findById(manufacturerId);
        if(currentEmp.isPresent()) {
            employeeDto.setId(manufacturerId);
            Manufacturer entity = manufacturerDtoMapper.convertToEntity(employeeDto);
            manufacturerRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{manufacturerId}")
    public ResponseEntity deleteManufacturer(@PathVariable Long manufacturerId)
    {
        manufacturerRepository.deleteById(manufacturerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    // ################################################
    private Link createManufacturerSelfLink(Long manufacturerId){
        return linkTo(methodOn(ManufacturerController.class).getManufacturerById(manufacturerId)).withSelfRel();
    }

    private Link createManufacturerProductsLink(Long manufacturerId){
        return linkTo(methodOn(ManufacturerController.class).getProductsOfManufacturerById(manufacturerId)).withSelfRel();
    }*/

}
