package ec.com.company.core.microservices.calculoinformaciondemografica;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ec.com.company.core.microservices.calculoinformaciondemografica.comparators.InformacionDemograficaComparator;
import ec.com.company.core.microservices.calculoinformaciondemografica.dtos.InformacionDemografica;
import ec.com.company.core.microservices.calculoinformaciondemografica.utils.GsonFormatterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CalculoInformacionDemograficaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @ParameterizedTest
    @CsvSource({"114760_request.json, 114760_response.json",
            "114980_request.json, 114980_response.json",
            "114614_request.json, 114614_response.json",
            "114934_request.json, 114934_response.json",
            "114713_request.json, 114713_response.json",
            "114808_request.json, 114808_response.json"}
    )
    public void testMicroserviceVscoreResults(String requestFilename, String responseFilename) throws Exception {
        byte[] requestContent = loadResourceContent(requestFilename);
        String rawMicroserviceResponse = performRequest(requestContent);

        InformacionDemografica resultadoMicroservicio = parseMicroserviceResponse(rawMicroserviceResponse);
        InformacionDemografica resultadocore = parseJsonToInformacionDemografica(loadResourceContentAsString(responseFilename));

        compareInformacionDemografica(resultadoMicroservicio, resultadocore);
    }

    private InformacionDemografica parseMicroserviceResponse(String rawMicroserviceResponse){
        Gson gson = GsonFormatterUtil.getInstance().getGson();
        JsonObject jsonObject = gson.fromJson(rawMicroserviceResponse, JsonObject.class);
        JsonObject contentJson = jsonObject.getAsJsonObject("content");
        return gson.fromJson(contentJson, InformacionDemografica.class);
    }

    private InformacionDemografica parseJsonToInformacionDemografica(String jsonContent) {
        Gson gson = GsonFormatterUtil.getInstance().getGson();
        return gson.fromJson(jsonContent, InformacionDemografica.class);
    }

    private byte[] loadResourceContent(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        return Files.readAllBytes(resource.getFile().toPath());
    }

    private String loadResourceContentAsString(String filename) throws IOException {
        return new String(loadResourceContent(filename));
    }

    private String performRequest(byte[] requestContent) throws Exception {
        MvcResult microserviceResult = mockMvc.perform(post("/v1")
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return microserviceResult.getResponse().getContentAsString();
    }



    private void compareInformacionDemografica(InformacionDemografica resultadoMicroservicio, InformacionDemografica resultadocore) {
        Comparator<InformacionDemografica> comparator = new InformacionDemograficaComparator();
        int areEqual = comparator.compare(resultadoMicroservicio, resultadocore);
        Assertions.assertEquals(0, areEqual);
    }
}
