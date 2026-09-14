package com.estudo.agendadortarefas.infrastructure.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String mensage){
        super(mensage);
    }

    public ResourceNotFound(String mensage, Throwable throwable){
        super(mensage, throwable);
    }

}
