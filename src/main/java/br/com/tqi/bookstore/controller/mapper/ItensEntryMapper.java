package br.com.tqi.bookstore.controller.mapper;


import br.com.tqi.bookstore.controller.dto.ItensEntryCreateDTO;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.controller.dto.ItensEntryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItensEntryMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ItensEntryDTO toItensEntryDTO(ItensEntry itensEntry){
        return MODEL_MAPPER.map(itensEntry, ItensEntryDTO.class);
    }
    public List<ItensEntryDTO> toItensEntryDTOList(List<ItensEntry> itensEntryList) {
        return itensEntryList.stream().map(this::toItensEntryDTO).collect(Collectors.toList());
    }
    public ItensEntry toItensEntry(ItensEntryDTO dto) {
        return MODEL_MAPPER.map(dto,ItensEntry.class);
    }
    public ItensEntry toItensEntryCreate(ItensEntryCreateDTO dto){
        return MODEL_MAPPER.map(dto, ItensEntry.class);
    }
}