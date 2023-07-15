package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import ec.com.company.core.microservices.calculoinformaciondemografica.constants.AppConstants;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Empleado(
        @JsonFormat(pattern = AppConstants.DATE_PATTERN, timezone = "America/Guayaquil")
        @NonNull
        LocalDate fechaIngresoJubilacion,
        @JsonFormat(pattern = AppConstants.DATE_PATTERN, timezone = "America/Guayaquil")
        @NonNull
        LocalDate fechaNacimiento,
        @NonNull
        Integer tipo,
        @JsonAlias("remuneracionPromedioJubilacion")
        @NonNull BigDecimal remuneracionPromedio,
        @JsonAlias("sexo")
        @NonNull
        String genero) {

}
