package digital_catalog_api.demo.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(UUID id) {

        super("Resource not found. Id " + id);
    }
}
