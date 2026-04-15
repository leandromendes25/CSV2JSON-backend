package com.leandromendes25.JSON2CSV.controller;

import com.leandromendes25.JSON2CSV.controler.ConverterController;
import com.leandromendes25.JSON2CSV.service.ConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ConverterControllerTest {
    @InjectMocks
    private ConverterController controller;
    @Mock
    private ConverterService converterService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String url;

    @BeforeEach
    void setup(){
        url = "/convert";
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    void deveConverterParaJsonComSucesso() throws Exception {
        String csv = "nome,idade\nleo,15";
        when(converterService.converterToJson(csv)).thenReturn(new ArrayList<>());
        mockMvc.perform(post(url.concat("/csv-to-json"))
                .contentType(MediaType.TEXT_PLAIN)
                        .accept(MediaType.APPLICATION_JSON)
                .content(csv)).andExpect(status().isOk());
    }
    @Test
    void deveFalharAoConverterParaJson() throws Exception {
        mockMvc.perform(post(url.concat("/csv-to-json"))
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
    @Test
    void deveConverterParaCsvComSucesso() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nome","leo");
        map.put("idade", "15");
        String mockEsperado = "nome, idade\nleo,15";
    when(converterService.converterToCSV(list)).thenReturn(mockEsperado);
    mockMvc.perform(post(url.concat("/json-to-csv"))
            .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(list))
            .accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }
    @Test
    void naoDeveConverterParaCsv() throws Exception {
        mockMvc.perform(post(url.concat("/json-to-csv"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)).andExpect(status().isBadRequest());
    }
}
