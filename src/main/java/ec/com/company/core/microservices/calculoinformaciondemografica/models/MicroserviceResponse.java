package ec.com.company.core.microservices.calculoinformaciondemografica.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MicroserviceResponse<T> {
    private Integer status;
    private String message;
    private T content;
}
