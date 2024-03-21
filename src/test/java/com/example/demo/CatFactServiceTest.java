package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClient;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({CatFactController.class})
@Import({CatFactService.class})
class CatFactServiceTest {

    private MockRestServiceServer server;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestClient.Builder restClientBuilder;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.bindTo(restClientBuilder).build();
    }

    @Test
    void test() throws Exception {
        server.expect(requestTo("http://localhost:8080/fact"))
                .andRespond(withSuccess("""
                        	{"fact":"In 1987 cats overtook dogs as the number one pet in America."}
                        """, APPLICATION_JSON));

        var result = mockMvc.perform(get("/fact"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.fact", is("In 1987 cats overtook dogs as the number one pet in America.")));

        server.verify();
    }
}