package com.estudo.agendadortarefas.business.mapper;

import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-11T17:41:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.20 (Oracle Corporation)"
)
@Component
public class TarefasConverterImpl implements TarefasConverter {

    @Override
    public TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO) {
        if ( tarefasDTO == null ) {
            return null;
        }

        TarefasEntity.TarefasEntityBuilder tarefasEntity = TarefasEntity.builder();

        tarefasEntity.id( tarefasDTO.getId() );
        tarefasEntity.nomeTarefa( tarefasDTO.getNomeTarefa() );
        tarefasEntity.descricao( tarefasDTO.getDescricao() );
        tarefasEntity.dataCriacao( tarefasDTO.getDataCriacao() );
        tarefasEntity.dataEvento( tarefasDTO.getDataEvento() );
        tarefasEntity.emailUsuario( tarefasDTO.getEmailUsuario() );
        tarefasEntity.dataAlteracao( tarefasDTO.getDataAlteracao() );
        tarefasEntity.statusNotificacaoEnum( tarefasDTO.getStatusNotificacaoEnum() );

        return tarefasEntity.build();
    }

    @Override
    public TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity) {
        if ( tarefasEntity == null ) {
            return null;
        }

        TarefasDTO.TarefasDTOBuilder tarefasDTO = TarefasDTO.builder();

        tarefasDTO.id( tarefasEntity.getId() );
        tarefasDTO.nomeTarefa( tarefasEntity.getNomeTarefa() );
        tarefasDTO.descricao( tarefasEntity.getDescricao() );
        tarefasDTO.dataCriacao( tarefasEntity.getDataCriacao() );
        tarefasDTO.dataEvento( tarefasEntity.getDataEvento() );
        tarefasDTO.emailUsuario( tarefasEntity.getEmailUsuario() );
        tarefasDTO.dataAlteracao( tarefasEntity.getDataAlteracao() );
        tarefasDTO.statusNotificacaoEnum( tarefasEntity.getStatusNotificacaoEnum() );

        return tarefasDTO.build();
    }

    @Override
    public List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOs) {
        if ( tarefasDTOs == null ) {
            return null;
        }

        List<TarefasEntity> list = new ArrayList<TarefasEntity>( tarefasDTOs.size() );
        for ( TarefasDTO tarefasDTO : tarefasDTOs ) {
            list.add( paraTarefasEntity( tarefasDTO ) );
        }

        return list;
    }

    @Override
    public List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities) {
        if ( tarefasEntities == null ) {
            return null;
        }

        List<TarefasDTO> list = new ArrayList<TarefasDTO>( tarefasEntities.size() );
        for ( TarefasEntity tarefasEntity : tarefasEntities ) {
            list.add( paraTarefasDTO( tarefasEntity ) );
        }

        return list;
    }
}
