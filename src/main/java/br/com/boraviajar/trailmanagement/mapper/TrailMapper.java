package br.com.boraviajar.trailmanagement.mapper;

import br.com.boraviajar.trailmanagement.dto.TrailDTO;
import br.com.boraviajar.trailmanagement.model.Trail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrailMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TrailDTO convertToDTO(Trail trail){
        return this.modelMapper.map(trail, TrailDTO.class);
    }

    public Trail convertToModel(TrailDTO trailDTO){
        return this.modelMapper.map(trailDTO, Trail.class);
    }
}
