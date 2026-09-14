package com.estudo.agendadortarefas.controller;

import com.estudo.agendadortarefas.business.TarefasService;
import com.estudo.agendadortarefas.business.dto.TarefasDTO;
import com.estudo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO tarefasDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        return ResponseEntity.ok(tarefasService.listarTarefasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorUsuario(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.listarTarefasPorUsuario(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam String id) {
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> atualizarStatusNotificacao(@RequestParam("id") String id, @RequestParam("status") StatusNotificacaoEnum status) {
        return ResponseEntity.ok(tarefasService.atualizarStatusNotificacao(id, status));
    }


    @PutMapping
    public ResponseEntity<TarefasDTO> atualizarTarefa(@RequestParam String id, @RequestBody TarefasDTO tarefasDTO) {
        return ResponseEntity.ok(tarefasService.updateTarefas(id, tarefasDTO));
    }

}
