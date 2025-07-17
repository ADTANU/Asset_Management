package com.hexaware.asset.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hexaware.asset.entity.Asset;
import com.hexaware.asset.service.AssetService;

@WebMvcTest(controllers = EmployeeController.class)
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetService assetService;

    @Test
    void testGetAllAssets() throws Exception {
        List<Asset> assets = List.of(new Asset(1L, "Laptop", "Dell"), new Asset(2L, "Monitor", "Samsung"));
        when(assetService.getAllAssets()).thenReturn(assets);

        mockMvc.perform(get("/api/employee/assets"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(2));
    }
}
