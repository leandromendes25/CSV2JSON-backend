package com.leandromendes25.JSON2CSV.controler;

import com.leandromendes25.JSON2CSV.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/convert")
public class ConverterController {
    @Autowired
    private ConverterService service;
    @PostMapping("/json-to-csv")
    public ResponseEntity<String> converterToCSV(@RequestBody List<Map<String, Object>> dados){
      return ResponseEntity.ok().body(service.converterToCSV(dados));
    }
@PostMapping("/csv-to-json")
    public ResponseEntity<List<Map<String,Object>>> converterToJson(@RequestBody String dados){
        return ResponseEntity.ok().body(service.converterToJson(dados));
}
}
