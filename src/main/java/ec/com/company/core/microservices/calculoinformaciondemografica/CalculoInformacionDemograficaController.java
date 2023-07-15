package ec.com.company.core.microservices.calculoinformaciondemografica;


import ec.com.company.core.microservices.calculoinformaciondemografica.calculators.CalculadoraInformacionDemografica;
import ec.com.company.core.microservices.calculoinformaciondemografica.dtos.Estudio;
import ec.com.company.core.microservices.calculoinformaciondemografica.dtos.InformacionDemografica;
import ec.com.company.core.microservices.calculoinformaciondemografica.exceptions.coreException;
import ec.com.company.core.microservices.calculoinformaciondemografica.models.MicroserviceResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/")
class CalculoInformacionDemograficaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculoInformacionDemograficaController.class);

    @CrossOrigin()
    @PostMapping(
            value = "/v1",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> calcularInformacionDemografica(@Valid @RequestBody Estudio estudio)
            throws coreException {

        String numeroProceso = estudio.numeroProceso();
        LOGGER.info("Received POST request for estudio with numero proceso: {}", numeroProceso);
        final long startTime = System.nanoTime();

        try (MDC.MDCCloseable ignored = MDC.putCloseable("numeroProceso", numeroProceso)) {
            InformacionDemografica informacionDemografica = CalculadoraInformacionDemografica.calcular(estudio);
            MicroserviceResponse<InformacionDemografica> response = createSuccessfulResponse(informacionDemografica);
            logOperationTime(startTime);
            return ResponseEntity.ok(response);
        } catch (coreException e) {
            MicroserviceResponse<String> errorResponse = createErrorResponse(e);
            logOperationTime(startTime);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    private static void logOperationTime(long startTime) {
        final long estimatedTime = System.nanoTime() - startTime;
        long estimatedTimeInSeconds = Duration.ofNanos(estimatedTime).getSeconds();
        LOGGER.info("Success. Operation took {} seconds", estimatedTimeInSeconds);
    }

    private static MicroserviceResponse<InformacionDemografica> createSuccessfulResponse(
            InformacionDemografica informacionDemografica) {
        int statusCode = 0; // Blame coworker for returning 0 instead of HttpStatus.OK.value() XD
        String statusMessage = HttpStatus.OK.getReasonPhrase();
        LOGGER.info("Sending the response back with status code: {}", statusCode);
        return MicroserviceResponse.<InformacionDemografica>builder()
                .status(statusCode)
                .message(statusMessage)
                .content(informacionDemografica)
                .build();
    }

    private static MicroserviceResponse<String> createErrorResponse(coreException coreException) {
        int statusCode = coreException.getErrorCode().value();
        String statusMessage = coreException.getErrorCode().getReasonPhrase();
        String errorMessage = coreException.getMessage();
        LOGGER.error("Sending the response back with status code: {}", statusCode);
        return MicroserviceResponse.<String>builder()
                .status(statusCode)
                .message(statusMessage)
                .content(errorMessage)
                .build();
    }
}