package ec.com.company.core.microservices.calculoinformaciondemografica.constants;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public abstract class AppConstants {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final int JSONS_MAX_NUMERIC_SCALE = 9;
    public static final int RESPONSE_BIGDECIMALS_MAX_SCALE = 4;
    public static final RoundingMode JSONS_NUMERIC_ROUNDING_MODE = RoundingMode.HALF_UP;

    // 10 was chosen because it is the default scale for BigDecimal
    public static final int BIG_DECIMAL_CALCULATIONS_SCALE = 10;
}
