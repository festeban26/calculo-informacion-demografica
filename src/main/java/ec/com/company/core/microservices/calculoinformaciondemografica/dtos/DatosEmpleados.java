package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class DatosEmpleados {

    @NonNull Boolean esActivo;
    @NonNull Boolean esTrabajadorJubilado;
    @NonNull Boolean esTrabajadorJubiladoPendiente;
    @NonNull Boolean esTrabajadorSalido;
    @NonNull Integer tw;
    @NonNull
    Integer edad;
    @NonNull
    BigDecimal ts;
    @NonNull
    Integer tipoCalculado;
    @NonNull
    String genero;
    @NonNull
    BigDecimal tf;
    @NonNull
    BigDecimal sueldo;
    @NonNull
    BigDecimal vidaLaboralRemanente;
    @NonNull
    BigDecimal ingresoMensualActivos;
    @NonNull
    BigDecimal pensionMensualJubilados;
    @NonNull
    BigDecimal ingresoNomina;

}
