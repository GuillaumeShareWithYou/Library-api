package com.guillaume.libraryapi.exception;

public class ResourcesNotFoundException extends Exception {

   public ResourcesNotFoundException(String ressourceName){
        super("Resources : "+ressourceName+" not found");
    }
}
