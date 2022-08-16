package br.com.tqi.bookstore.controller.mapper;

import br.com.tqi.bookstore.controller.dto.ItensSellDTO;
import br.com.tqi.bookstore.controller.dto.create.ItensSellCreateDTO;
import br.com.tqi.bookstore.model.ItensSell;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItensSellMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ItensSellDTO toItensSellDTO(ItensSell itensSell){
        return MODEL_MAPPER.map(itensSell, ItensSellDTO.class);
    }
    public List<ItensSellDTO> toItensSellDTOList(List<ItensSell> itensSellList) {
        return itensSellList.stream().map(this::toItensSellDTO).collect(Collectors.toList());
    }
    public ItensSell toItensSell(ItensSellDTO dto) {
        return MODEL_MAPPER.map(dto,ItensSell.class);
    }
    public ItensSell toItensSellCreate(ItensSellCreateDTO dto){
        return MODEL_MAPPER.map(dto, ItensSell.class);
    }
}
