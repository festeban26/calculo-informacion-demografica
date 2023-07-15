package ec.com.company.core.microservices.calculoinformaciondemografica.comparators;

import ec.com.company.core.microservices.calculoinformaciondemografica.dtos.InformacionDemografica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Objects;

public class InformacionDemograficaComparator implements Comparator<InformacionDemografica> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InformacionDemograficaComparator.class);

    @Override
    public int compare(InformacionDemografica informacionDemografica1, InformacionDemografica informacionDemografica2) {
        LOGGER.debug("Comparing InformacionDemografica objects");

        Objects.requireNonNull(informacionDemografica1, "informacionDemografica1 cannot be null");
        Objects.requireNonNull(informacionDemografica2, "informacionDemografica2 cannot be null");

        LOGGER.debug("informacionDemografica1: {}", informacionDemografica1);
        LOGGER.debug("informacionDemografica2: {}", informacionDemografica2);

        if (!informacionDemografica1.equals(informacionDemografica2)) {
            LOGGER.debug("Objects are not equal. Returning 1.");
            return 1;
        }

        // If we reach this point, the two objects are the same
        LOGGER.debug("Objects are equal. Returning 0.");
        return 0;
    }
}
