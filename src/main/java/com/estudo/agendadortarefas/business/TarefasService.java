package com.estudo.agendadortarefas.business;

import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.business.mapper.TarefasConverter;
import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.estudo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.estudo.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.estudo.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {
        String emailUsuario = jwtUtil.extrairEmailToken(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(emailUsuario);
        TarefasEntity tarefasEntity = tarefasConverter.paraTarefasEntity(tarefasDTO);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTO> listarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByDataEventoBetween(dataInicio, dataFim);
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public List<TarefasDTO> listarTarefasPorUsuario(String token) {
        String emailUsuario = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByEmailUsuario(emailUsuario);
        return tarefasConverter.paraListaTarefasDTO(tarefasEntities);
    }

}
