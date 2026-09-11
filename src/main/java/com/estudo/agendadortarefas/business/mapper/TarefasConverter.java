package com.estudo.agendadortarefas.business.mapper;

import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);
}
