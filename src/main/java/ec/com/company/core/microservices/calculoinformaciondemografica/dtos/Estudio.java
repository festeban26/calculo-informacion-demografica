package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import jakarta.validation.Valid;
import lombok.NonNull;

import java.util.List;


// Check if value annotations works, as this object is created on json request

public record Estudio(@NonNull String numeroProceso,
                      @Valid @NonNull Parametros parametros,
                      @Valid @NonNull Empresa empresa,
                      @Valid @NonNull Hipotesiscompanyl hipotesiscompanyl,
                      @NonNull List<Empleado> empleados) {
}
