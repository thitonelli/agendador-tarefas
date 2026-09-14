package com.estudo.agendadortarefas.business.mapper;

import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefasEntity);

}
