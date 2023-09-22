package br.com.jonatas.sectorservice.controllers;

import br.com.jonatas.sectorservice.dto.ResponseMessage;
import br.com.jonatas.sectorservice.models.Sector;
import br.com.jonatas.sectorservice.services.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping("/{name}")
    ResponseEntity<Sector> get(@PathVariable("name") String name) {
        var sector = sectorService.getByName(name);
        return ResponseEntity.ok(sector);
    }

    @PostMapping("register")
    ResponseEntity<ResponseMessage> register(@RequestBody Sector sector) {
        var sectorSaved = sectorService.save(sector);

        URI uri = UriComponentsBuilder.fromUriString("/v1/sectors/{id}")
                .buildAndExpand(sectorSaved)
                .toUri();

        ResponseMessage response = new ResponseMessage("Setor cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(response);
    }

}
