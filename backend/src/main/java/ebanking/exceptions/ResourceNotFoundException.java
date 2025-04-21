package ebanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private final String resourceId;

    public ResourceNotFoundException(String resourceType, String id) {
        super(String.format("%s with id %s not found", resourceType, id));
        this.resourceId = id;
    }
}