package com.example.datageneratormicroservice.web.controller;

import com.example.datageneratormicroservice.model.Data;
import com.example.datageneratormicroservice.model.DataTestOptions;
import com.example.datageneratormicroservice.service.KafkaDataServiceImpl;
import com.example.datageneratormicroservice.service.TestDataServiceImpl;
import com.example.datageneratormicroservice.web.dto.DataDto;
import com.example.datageneratormicroservice.web.dto.DataTestOptionsDto;
import com.example.datageneratormicroservice.web.mapper.DataMapper;
import com.example.datageneratormicroservice.web.mapper.DataTestOptionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {
    private final KafkaDataServiceImpl kafkaDataService;
    private final TestDataServiceImpl testDataService;
    private final DataTestOptionsMapper dataTestOptionsMapper;
    private final DataMapper dataMapper;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dto) {
        Data data = dataMapper.toEntity(dto);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto dataTestOptionsDto) {
        DataTestOptions dataTestOptions = dataTestOptionsMapper.toEntity(dataTestOptionsDto);
        testDataService.sendMessage(dataTestOptions);
    }

}

