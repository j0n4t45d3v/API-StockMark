package br.com.jonatas.sectorservice.services;

import br.com.jonatas.sectorservice.error.exceptions.ConflictException;
import br.com.jonatas.sectorservice.error.exceptions.NotFoundException;
import br.com.jonatas.sectorservice.models.Product;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SectorServiceImpTest {
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
        assertThrows(NotFoundException.class, () -> this.service.getByName("SETOR"), "Setor encontrado!");
    }

    @Test
    void shouldSaveSector() {
        when(this.sectorRepository.save(any(Sector.class))).thenReturn(sector);

        String sectorSaved = this.service.save(sector);

        verify(this.sectorRepository).save(any(Sector.class));

        assertNotNull(sectorSaved);
        assertEquals("Setor criado!", sectorSaved);
    }

    @Test
    void shouldSaveSectorAlreadyExists() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.of(sector));
        assertThrows(ConflictException.class, () -> this.service.save(sector));
    }

    @Test
    void shouldAddProduct() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.of(sector));
        when(this.sectorRepository.save(any(Sector.class))).thenReturn(sector);

        Product newProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 1")
                .price(10.0)
                .barcode("123456789")
                .quantity(10L)
                .description("Product 1 description")
                .build();

        String addProduct = this.service.addProduct("SETOR", newProduct);

        verify(this.sectorRepository).findByName(anyString());
        verify(this.sectorRepository).save(any(Sector.class));

        assertNotNull(addProduct);
        assertEquals("Novo produto adicionado!", addProduct);
        assertEquals(1, sector.getProducts().size());
    }

    @Test
    void shouldAddProductSectorNotFound() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.empty());

        Product product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 1")
                .price(10.0)
                .barcode("123456789")
                .quantity(10L)
                .description("Product 1 description")
                .build();

        assertThrows(NotFoundException.class, () -> this.service.addProduct("SETOR", product));
    }

    @Test
    void shouldDeleteSector() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.of(sector));
        doNothing().when(this.sectorRepository).delete(any(Sector.class));

        String deleteSector = this.service.deleteSector("SETOR");

        verify(this.sectorRepository).findByName(anyString());
        verify(this.sectorRepository).delete(any(Sector.class));

        assertNotNull(deleteSector);
        assertEquals("Setor deletado!", deleteSector);
    }

    @Test
    void shouldDeleteSectorNotFound() {
        when(this.sectorRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> this.service.deleteSector("SETOR"));
    }
}
