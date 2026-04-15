package com.leandromendes25.JSON2CSV.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConverterServiceTest {

    @InjectMocks
    private ConverterService converterService;
    @Test
    void deveConverterJsonParaCsvComSucesso(){
        List<Map<String, Object>> dados = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("nome", "leo");
        map1.put("idade", 15);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("nome", "joão");
        map2.put("cidade", "SP");

        dados.add(map1);
        dados.add(map2);

        String resultado =  converterService.converterToCSV(dados);
        assertTrue(resultado.contains("nome"));
        assertTrue(resultado.contains("idade"));
        assertTrue(resultado.contains("cidade"));
        assertTrue(resultado.contains("leo"));
        assertTrue(resultado.contains("joão"));
        assertTrue(resultado.contains("SP"));
    }
    @Test
    void deveFalharAoTentarConverterJsonParaCsv(){
        List<Map<String, Object>> dados = new ArrayList<>();
        RuntimeException e = assertThrows(RuntimeException.class, () -> converterService.converterToCSV(dados));
        assertEquals(e.getMessage(),"Dados não podem ser nulos");
    }
    @Test
    void deveConverterCsvParaJson(){
        String csv = """
                nome,idade,cidade
                leo,15,
                joão,,SP
                """;

        List<Map<String, Object>> list = converterService.converterToJson(csv);
        assertEquals(2, list.size());
        assertEquals("leo", list.get(0).get("nome"));
        assertEquals("15", list.get(0).get("idade"));
        assertEquals("", list.get(0).get("cidade"));

        assertEquals("joão", list.get(1).get("nome"));
        assertEquals("", list.get(1).get("idade"));
        assertEquals("SP", list.get(1).get("cidade"));
    }
    @Test
    void naoDeveConverterDeCsvParaJson(){
        String dados = "";
        RuntimeException e = assertThrows(RuntimeException.class, () -> converterService.converterToJson(dados));
        assertEquals(e.getMessage(),"Os dados não podem ser vázios");
    }
}
