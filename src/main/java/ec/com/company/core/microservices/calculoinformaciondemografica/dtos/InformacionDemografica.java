package ec.com.company.core.microservices.calculoinformaciondemografica.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ec.com.company.core.microservices.calculoinformaciondemografica.serializers.ScaledBigDecimalSerializer;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
@Builder
public class InformacionDemografica {

    private static final Logger LOGGER = LoggerFactory.getLogger(InformacionDemografica.class);

    @NonNull
    Integer trabajadoresActivos;
    @NonNull
    Integer trabajadoresJubilados;
    @NonNull
    Integer trabajadoresJubiladosPendientes;
    @NonNull
    Integer trabajadoresSalidos;
    @NonNull
    Integer trabajadoresTipo1Hombres;
    @NonNull
    Integer trabajadoresTipo1Mujeres;
    @NonNull
    Integer trabajadoresTipo2Hombres;
    @NonNull
    Integer trabajadoresTipo2Mujeres;
    @NonNull
    Integer trabajadoresTipo3Hombres;
    @NonNull
    Integer trabajadoresTipo3Mujeres;
    @NonNull
    Integer trabajadoresTipo4Hombres;
    @NonNull
    Integer trabajadoresTipo4Mujeres;

    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal tsPromedioActivos;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal tfPromedioActivos;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal edadPromedioActivos;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal nominaTotalActivos;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal nominaTotalJubilados;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal vidaLaboralPromedioRemanente;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal ingresoMensualPromedioActivos;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal pensionMensualPromedioJubilados;
    @NonNull
    @JsonSerialize(using = ScaledBigDecimalSerializer.class)
    BigDecimal ingresoNomina;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InformacionDemografica that = (InformacionDemografica) o;

        // Compare integer fields
        return compareFieldsAndLogDifference("trabajadoresActivos", this.trabajadoresActivos, that.trabajadoresActivos)
                && compareFieldsAndLogDifference("trabajadoresJubilados", this.trabajadoresJubilados, that.trabajadoresJubilados)
                && compareFieldsAndLogDifference("trabajadoresJubiladosPendientes", this.trabajadoresJubiladosPendientes, that.trabajadoresJubiladosPendientes)
                && compareFieldsAndLogDifference("trabajadoresSalidos", this.trabajadoresSalidos, that.trabajadoresSalidos)
                && compareFieldsAndLogDifference("trabajadoresTipo1Hombres", this.trabajadoresTipo1Hombres, that.trabajadoresTipo1Hombres)
                && compareFieldsAndLogDifference("trabajadoresTipo1Mujeres", this.trabajadoresTipo1Mujeres, that.trabajadoresTipo1Mujeres)
                && compareFieldsAndLogDifference("trabajadoresTipo2Hombres", this.trabajadoresTipo2Hombres, that.trabajadoresTipo2Hombres)
                && compareFieldsAndLogDifference("trabajadoresTipo2Mujeres", this.trabajadoresTipo2Mujeres, that.trabajadoresTipo2Mujeres)
                && compareFieldsAndLogDifference("trabajadoresTipo3Hombres", this.trabajadoresTipo3Hombres, that.trabajadoresTipo3Hombres)
                && compareFieldsAndLogDifference("trabajadoresTipo3Mujeres", this.trabajadoresTipo3Mujeres, that.trabajadoresTipo3Mujeres)
                && compareFieldsAndLogDifference("trabajadoresTipo4Hombres", this.trabajadoresTipo4Hombres, that.trabajadoresTipo4Hombres)
                && compareFieldsAndLogDifference("trabajadoresTipo4Mujeres", this.trabajadoresTipo4Mujeres, that.trabajadoresTipo4Mujeres)
                // Compare BigDecimal fields
                && compareFieldsAndLogDifference("tsPromedioActivos", this.tsPromedioActivos, that.tsPromedioActivos)
                && compareFieldsAndLogDifference("tfPromedioActivos", this.tfPromedioActivos, that.tfPromedioActivos)
                && compareFieldsAndLogDifference("edadPromedioActivos", this.edadPromedioActivos, that.edadPromedioActivos)
                && compareFieldsAndLogDifference("nominaTotalActivos", this.nominaTotalActivos, that.nominaTotalActivos)
                && compareFieldsAndLogDifference("nominaTotalJubilados", this.nominaTotalJubilados, that.nominaTotalJubilados)
                && compareFieldsAndLogDifference("vidaLaboralPromedioRemanente", this.vidaLaboralPromedioRemanente, that.vidaLaboralPromedioRemanente)
                && compareFieldsAndLogDifference("ingresoMensualPromedioActivos", this.ingresoMensualPromedioActivos, that.ingresoMensualPromedioActivos)
                && compareFieldsAndLogDifference("pensionMensualPromedioJubilados", this.pensionMensualPromedioJubilados, that.pensionMensualPromedioJubilados)
                && compareFieldsAndLogDifference("ingresoNomina", this.ingresoNomina, that.ingresoNomina);
    }

    private boolean compareFieldsAndLogDifference(String fieldName, Integer thisValue, Integer thatValue) {
        if (!thisValue.equals(thatValue)) {
            LOGGER.error("Field {} is different: {} vs {}", fieldName, thisValue, thatValue);
            return false;
        }

        return true;
    }

    private boolean compareFieldsAndLogDifference(String fieldName, BigDecimal thisValue, BigDecimal thatValue) {

        if (!isBigDecimalEqual(thisValue, thatValue)) {
            LOGGER.error("Field {} is different: {} vs {}", fieldName, thisValue, thatValue);
            return false;
        }
        return true;
    }

    private final static int NUMBER_OF_DECIMAL_PLACES_TO_COMPARE = 2;

    private static boolean isBigDecimalEqual(BigDecimal bd1, BigDecimal bd2) {
        // If both BigDecimals are null, they are considered equal
        if (bd1 == null && bd2 == null) {
            return true;
        }

        // If either BigDecimal is null, they are not equal
        if (bd1 == null || bd2 == null) {
            return false;
        }

        // Set the scale and rounding mode for both BigDecimals
        BigDecimal bd1Scaled = bd1.setScale(InformacionDemografica.NUMBER_OF_DECIMAL_PLACES_TO_COMPARE, RoundingMode.HALF_UP);
        BigDecimal bd2Scaled = bd2.setScale(InformacionDemografica.NUMBER_OF_DECIMAL_PLACES_TO_COMPARE, RoundingMode.HALF_UP);

        // Compare the two scaled BigDecimals
        int comparisonResult = bd1Scaled.compareTo(bd2Scaled);

        // If the comparison result is not 0, the BigDecimals are not equal
        return comparisonResult == 0;
    }
}
