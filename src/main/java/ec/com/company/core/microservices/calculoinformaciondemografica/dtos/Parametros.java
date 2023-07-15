package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import ec.com.company.core.microservices.calculoinformaciondemografica.constants.AppConstants;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Parametros(
        @JsonFormat(pattern = AppConstants.DATE_PATTERN, timezone = "America/Guayaquil")
        @JsonAlias("fechaValoracion")
        @NotNull
        LocalDate fechaCalculo,
        @JsonFormat(pattern = AppConstants.DATE_PATTERN, timezone = "America/Guayaquil")
        @NotNull
        LocalDate fechaEstimacion) {

}
