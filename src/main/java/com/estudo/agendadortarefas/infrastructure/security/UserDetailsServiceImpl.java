package com.estudo.agendadortarefas.infrastructure.security;


import com.estudo.agendadortarefas.business.dto.UsuarioDTO;
import com.estudo.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;


    public UserDetails carregaUsuarioPorEmail(String email, String token) {
        UsuarioDTO usuarioDTO = usuarioClient.buscaUsuarioPorEmail("Bearer " + token, email);
        return org.springframework.security.core.userdetails.User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
