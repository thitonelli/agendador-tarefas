package com.estudo.agendadortarefas.infrastructure.repository;

import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.estudo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicio, LocalDateTime dataFim, StatusNotificacaoEnum statusNotificacaoEnum);

    List<TarefasEntity> findByEmailUsuario(String emailUsuario);

}
