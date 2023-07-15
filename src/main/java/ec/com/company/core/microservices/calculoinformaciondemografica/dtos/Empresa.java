package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import lombok.NonNull;

public record Empresa(
        @NonNull
        String estado
) {
}
