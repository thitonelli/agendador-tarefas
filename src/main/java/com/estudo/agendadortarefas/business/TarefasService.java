package com.estudo.agendadortarefas.business;

import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.estudo.agendadortarefas.business.mapper.TarefaConverter;
import com.estudo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.estudo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.estudo.agendadortarefas.infrastructure.exceptions.ResourceNotFound;
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
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {
        String emailUsuario = jwtUtil.extrairEmailToken(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(emailUsuario);
        TarefasEntity tarefasEntity = tarefaConverter.paraTarefasEntity(tarefasDTO);

        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTO> listarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByDataEventoBetween(dataInicio, dataFim);
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public List<TarefasDTO> listarTarefasPorUsuario(String token) {
        String emailUsuario = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> tarefasEntities = tarefasRepository.findByEmailUsuario(emailUsuario);
        return tarefaConverter.paraListaTarefasDTO(tarefasEntities);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        }catch (Exception e){
            throw new ResourceNotFound("id de tarefa não encontrado: " + id + " - " + e.getMessage());
        }
    }

    public TarefasDTO atualizarStatusNotificacao(String id, StatusNotificacaoEnum statusNotificacaoEnum) {
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound("id de tarefa não encontrado: " + id));
            tarefasEntity.setStatusNotificacaoEnum(statusNotificacaoEnum);
            tarefasEntity.setDataAlteracao(LocalDateTime.now());
            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
        }catch (Exception e){
            throw new ResourceNotFound("id de tarefa não encontrado: " + id + " - " + e.getMessage());
        }
    }

    public TarefasDTO updateTarefas(String id, TarefasDTO tarefasDTO) {
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound("id de tarefa não encontrado: " + id));
            tarefaUpdateConverter.updateTarefa(tarefasDTO, tarefasEntity);
            tarefasEntity.setDataAlteracao(LocalDateTime.now());
            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
        }catch (Exception e){
            throw new ResourceNotFound("id de tarefa não encontrado: " + id + " - " + e.getMessage());
        }
    }

}
