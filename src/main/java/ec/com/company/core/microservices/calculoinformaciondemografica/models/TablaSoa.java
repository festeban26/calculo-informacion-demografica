package ec.com.company.core.microservices.calculoinformaciondemografica.models;

import ec.com.company.core.microservices.calculoinformaciondemografica.exceptions.coreException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TablaSoa {

    private static TablaSoa instance;

    private final Map<Integer, BigDecimal> numeroActivosPorEdad;

    private static final int EDAD_INICIO = 30;

    private static final int EDAD_FIN = 70;


    public static TablaSoa getInstance() throws coreException {
        if (instance == null) {
            instance = new TablaSoa(
                    // Edades de 30 a 70 (inclusive). Esto debido a que los QX_NUMERO_ACTIVOS están definidos para esad edades.
                    IntStream.range(EDAD_INICIO, EDAD_FIN + 1).boxed().toArray(Integer[]::new));
        }
        return instance;
    }

    private TablaSoa(Integer[] edades) throws coreException {
        if (ArrayUtils.isEmpty(edades)) {
            throw new coreException("Error. El arreglo edades pasado al constructor de TablaSoa" +
                    " está vacío o es null.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (ArrayUtils.isEmpty(NUMERO_ACTIVOS)) {
            throw new coreException("Error. El arreglo numeroActivos pasado al constructor de TablaSoa" +
                    " está vacío o es null.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (edades.length != NUMERO_ACTIVOS.length) {
            throw new coreException("Error en creacion de TablaSoa. Los arreglos edades " + "[size=" + edades.length + "]" +
                    " y numeroActivos " + "[size=" + NUMERO_ACTIVOS.length + "]" + " deben ser arreglos paralelos" +
                    " por lo que deben tener el mismo tamaño.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        this.numeroActivosPorEdad = new HashMap<>();
        for (int i = 0; i < edades.length; i++) {
            this.numeroActivosPorEdad.put(edades[i], NUMERO_ACTIVOS[i]);
        }
    }

    private Map<Integer, BigDecimal> getNumeroActivosPorEdad() {
        return this.numeroActivosPorEdad;
    }

    public BigDecimal buscarActivos(Integer edad) throws coreException {

        if (edad < EDAD_INICIO) {
            edad = EDAD_INICIO;
        } else if (edad > EDAD_FIN) {
            edad = EDAD_FIN;
        }

        // Revisar si el map contiene data para la edad especificada
        if (!getNumeroActivosPorEdad().containsKey(edad)) {
            // Advertir al usuario el problema
            throw new coreException(
                    "Error inesperado. La TablaSoa no contiene un número de activos para la edad: " + edad,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Devolver el qxs de mortalidad correspondiente a la edad
        return getNumeroActivosPorEdad().get(edad);
    }

    private static final BigDecimal[] NUMERO_ACTIVOS = {
            BigDecimal.valueOf(100000),
            BigDecimal.valueOf(79910),
            BigDecimal.valueOf(65454),
            BigDecimal.valueOf(55524),
            BigDecimal.valueOf(49761),
            BigDecimal.valueOf(45730),
            BigDecimal.valueOf(42927),
            BigDecimal.valueOf(40893),
            BigDecimal.valueOf(39352),
            BigDecimal.valueOf(38053),
            BigDecimal.valueOf(36943),
            BigDecimal.valueOf(36000),
            BigDecimal.valueOf(35143),
            BigDecimal.valueOf(34363),
            BigDecimal.valueOf(33659),
            BigDecimal.valueOf(32989),
            BigDecimal.valueOf(32349),
            BigDecimal.valueOf(31734),
            BigDecimal.valueOf(31109),
            BigDecimal.valueOf(30506),
            BigDecimal.valueOf(29919),
            BigDecimal.valueOf(29350),
            BigDecimal.valueOf(28763),
            BigDecimal.valueOf(28185),
            BigDecimal.valueOf(27593),
            BigDecimal.valueOf(27006),
            BigDecimal.valueOf(26396),
            BigDecimal.valueOf(25786),
            BigDecimal.valueOf(25149),
            BigDecimal.valueOf(24505),
            BigDecimal.valueOf(23856),
            BigDecimal.valueOf(19991),
            BigDecimal.valueOf(18106),
            BigDecimal.valueOf(15130),
            BigDecimal.valueOf(13509),
            BigDecimal.valueOf(11246),
            BigDecimal.valueOf(6594),
            BigDecimal.valueOf(5145),
            BigDecimal.valueOf(3504),
            BigDecimal.valueOf(2040),
            BigDecimal.valueOf(987)
    };
}
