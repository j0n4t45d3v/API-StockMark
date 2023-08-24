package br.com.jonatas.sectorservice.services;

import br.com.jonatas.sectorservice.error.exceptions.ConflictException;
import br.com.jonatas.sectorservice.error.exceptions.NotFoundException;
import br.com.jonatas.sectorservice.models.Sector;
import br.com.jonatas.sectorservice.repositories.SectorRepository;
import br.com.jonatas.sectorservice.services.implementation.SectorServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectorServiceImpTest {
    @Mock
    private SectorRepository sectorRepository;
    @InjectMocks
    private SectorServiceImp service;
    private Sector sector;

    @BeforeEach
    void setUp() {
        sector = Sector.builder()
                .id(UUID.randomUUID())
                .name("Sector 1")
                .typeProduct("Frios")
                .products(new ArrayList<>())
                .employeeId(UUID.randomUUID())
                .build();
    }

    @Test
    void shouldReturnSector() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.of(sector));

        Sector sectorSaved = this.service.getByName(anyString());
        assertNotNull(sectorSaved);
        assertEquals(sector.getEmployeeId(), sectorSaved.getEmployeeId());
        assertEquals(sector.getName(), sectorSaved.getName());
        assertEquals(sector.getTypeProduct(), sectorSaved.getTypeProduct());
        assertEquals(sector.getProducts(), sectorSaved.getProducts());
        assertEquals(sector, sectorSaved);
    }

    @Test
    void shouldReturnSectorNull() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> this.service.getByName(anyString()), "Setor encontrado!");
    }

    @Test
    void shouldSaveSector() {
        when(this.sectorRepository.save(any(Sector.class))).thenReturn(sector);

        String sectorSaved = this.service.save(sector);
        assertNotNull(sectorSaved);
        assertEquals("Setor criado!", sectorSaved);
    }

    @Test
    void shouldSaveSectorAlreadyExists() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.of(sector));
        assertThrows(ConflictException.class, () -> this.service.save(sector), "Setor já existe!");
    }

}
