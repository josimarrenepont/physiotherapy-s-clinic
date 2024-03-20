package com.physiotherapy.s.clinic.service.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(Object id){
        super("Resource Not Found. Id " + id);
    }
}
