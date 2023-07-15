package ec.com.company.core.microservices.calculoinformaciondemografica.utils;

import com.google.gson.*;
import ec.com.company.core.microservices.calculoinformaciondemografica.constants.AppConstants;

import java.math.BigDecimal;
import java.time.LocalDate;


// Used to formar JSONS.
// This class is responsible for setting the number of decimal places on the json parsing
public class GsonFormatterUtil {

    private static GsonFormatterUtil instance;
    private Gson gson;

    public GsonFormatterUtil() {
    }

    public static GsonFormatterUtil getInstance() {
        if (instance == null) {
            instance = new GsonFormatterUtil();
        }
        return instance;
    }


    public Gson getGson() {
        if (this.gson == null) {
            this.gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext)
                            -> new JsonPrimitive(localDate.format(AppConstants.DATE_FORMATTER)))
                    .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (je, type, jdc)
                            -> LocalDate.parse(je.getAsString(), AppConstants.DATE_FORMATTER))
                    .registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                        BigDecimal value = BigDecimal.valueOf(src);
                        if (value.scale() > AppConstants.JSONS_MAX_NUMERIC_SCALE) {
                            value = value.setScale(AppConstants.JSONS_MAX_NUMERIC_SCALE, AppConstants.JSONS_NUMERIC_ROUNDING_MODE);
                        }
                        return new JsonPrimitive(value);
                    })
                    .registerTypeAdapter(BigDecimal.class, (JsonSerializer<BigDecimal>) (src, typeOfSrc, context) -> {
                        if (src.scale() > AppConstants.JSONS_MAX_NUMERIC_SCALE) {
                            src = src.setScale(AppConstants.JSONS_MAX_NUMERIC_SCALE, AppConstants.JSONS_NUMERIC_ROUNDING_MODE);
                        }
                        return new JsonPrimitive(src);
                    })
                    .create();
        }
        return this.gson;
    }
}
