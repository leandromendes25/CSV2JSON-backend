package com.leandromendes25.JSON2CSV.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConverterService {

    public String converterToCSV(List<Map<String, Object>> dados){
        if(dados.isEmpty()) throw new RuntimeException("Dados não podem ser nulos");
        Set<String> colunas = new LinkedHashSet<>();
        StringBuilder csv = new StringBuilder();
        for (Map<String, Object> map : dados) {
            colunas.addAll(map.keySet());//está pegando as colunas
        }
        for (String coluna : colunas){//passa por cada coluna e adiciona 1 ,
            csv.append(coluna).append(",");
        }
        csv.setLength(csv.length() -1);//remove a ultima virgula
        csv.append("\n");
        StringBuilder line = new StringBuilder();
        for (Map<String, Object> map : dados){
            for (String coluna : colunas){
                Object conteudo = map.get(coluna);
                String texto = (conteudo != null) ? conteudo.toString() : "";
                if(texto.contains(",")){
                    texto = "\"" + texto + "\"";
                }
                line.append(texto).append(",");
            }
            line.setLength(line.length() -1);
        }
        csv.append(line).append("\n");
        return csv.toString();
    }
    public List<Map<String, Object>> converterToJson(String dados){
        if (dados.isEmpty()) throw new RuntimeException("Os dados não podem ser vázios");
        String[] linhas = dados.trim().split("\\r?\\n");
        String[] chaves = linhas[0].split(",");
        List<Map<String, Object>> json = new ArrayList<>();
        for (int i = 1; i < linhas.length; i++){
            String[] linhasConteudo = linhas[i].split(",");
            Map<String, Object> map = new LinkedHashMap<>();

            for (int j = 0; j < chaves.length; j++){
            String valor = (j < linhasConteudo.length) ? linhasConteudo[j] : "";
            map.put(chaves[j], valor);
            }
            json.add(map);
        }
    return json;
    }
    }
