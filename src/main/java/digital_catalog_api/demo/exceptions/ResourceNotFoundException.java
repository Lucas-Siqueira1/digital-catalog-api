package digital_catalog_api.demo.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(UUID id) {

        super("Recurso não encontrado. Id " + id);
    }

    public ResourceNotFoundException(String id) {
        super("Recurso não encontrado. Id " + id);
    }
}
