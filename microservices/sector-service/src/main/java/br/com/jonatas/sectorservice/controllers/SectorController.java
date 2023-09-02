package br.com.jonatas.sectorservice.controllers;

import br.com.jonatas.sectorservice.models.Sector;
import br.com.jonatas.sectorservice.services.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<String> register(@RequestBody Sector sector) {
        var sectorSaved = sectorService.save(sector);
        return ResponseEntity.ok(sectorSaved);
    }

}
