package com.hexaware.asset.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.asset.entity.Asset;
import com.hexaware.asset.repository.AssetRepository;

class AssetServiceTest {

    @InjectMocks
    private AssetService assetService;

    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAssets_ReturnsList() {
        List<Asset> mockAssets = Arrays.asList(new Asset(1L, "Laptop", "Dell"), new Asset(2L, "Chair", "Ergo"));

        when(assetRepository.findAll()).thenReturn(mockAssets);

        List<Asset> result = assetService.getAllAssets();
        assertEquals(2, result.size());
        verify(assetRepository, times(1)).findAll();
    }

    @Test
    void testGetAssetById_NotFound() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> assetService.getAssetById(99L));
    }
}
