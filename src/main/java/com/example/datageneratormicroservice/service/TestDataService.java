package com.example.datageneratormicroservice.service;

import com.example.datageneratormicroservice.model.DataTestOptions;

public interface TestDataService {
    void sendMessage(DataTestOptions dataTestOptions);
}
