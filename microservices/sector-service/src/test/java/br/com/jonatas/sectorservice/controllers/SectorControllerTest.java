package br.com.jonatas.sectorservice.controllers;

import br.com.jonatas.sectorservice.models.Sector;
import br.com.jonatas.sectorservice.services.SectorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SectorController.class)
class SectorControllerTest {

    @MockBean
    private SectorService sectorService;

    @Autowired
    private MockMvc mockMvc;

    static Sector sector;

    @BeforeAll
    static void setup() {
        sector = Sector.builder()
                .id(UUID.randomUUID())
                .name("setor")
                .typeProduct("produto")
                .employeeId(UUID.randomUUID())
                .products(List.of())
                .build();
    }


    @Test
    void shouldReturnSector() throws Exception {

        when(this.sectorService.getByName(Mockito.anyString())).thenReturn(SectorControllerTest.sector);

        mockMvc.perform(get("/v1/sectors/{name}", "setor"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    void shouldCreateSector() throws Exception {

        when(this.sectorService.getByName(Mockito.anyString())).thenReturn(SectorControllerTest.sector);

        mockMvc.perform(post("/v1/sectors/register")
                .contentType("application/json")
                .content("{\"name\":\"setor\"," +
                        "\"typeProduct\":\"produto\"," +
                        "\"employeeId\":\""+ UUID.randomUUID() +"\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));

    }@Test
    void shouldAddProduct() throws Exception {


    }@Test
    void shouldDeleteSector() throws Exception {


    }
}
