package edu.pja.sri.s25666.sri02.dto.mapper;


//usuwamy zasób, który nie istnieje
//jeżeli wypadnie błąd 500 to wtedy jest zle
// trzeba zrobić tak zeby wywalilo sie 400
//trzeba sprawdzić czy istnieje w bazie jezeli nie to zwróc blad 400, a jezeli tak to usun i
//zwroc odpowiedni kod błedu

import edu.pja.sri.s25666.sri02.dto.ManufacturerDetailsDto;
import edu.pja.sri.s25666.sri02.dto.ManufacturerDto;
import edu.pja.sri.s25666.sri02.model.Manufacturer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ManufacturerDtoMapper {
    private final ModelMapper modelMapper;

    public ManufacturerDetailsDto convertToDetailsDto(Manufacturer m){
        return modelMapper.map(m, ManufacturerDetailsDto.class);
    }

    public ManufacturerDto convertToDto(Manufacturer m){
        return modelMapper.map(m, ManufacturerDto.class);
    }

    //to jest niepotrzebne bo z details dto nie będziemy transformować na entity
/*    private Manufacturer convertToEntity(ManufacturerDetailsDto detailsDto){
        return modelMapper.map(detailsDto, Manufacturer.class);
    }*/

    private Manufacturer convertToEntity(ManufacturerDto dto){
        return modelMapper.map(dto, Manufacturer.class);
    }
}




