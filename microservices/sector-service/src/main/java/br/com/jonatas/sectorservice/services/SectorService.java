package br.com.jonatas.sectorservice.services;

import br.com.jonatas.sectorservice.models.Sector;

public interface SectorService {
    String save(Sector sector);
    Sector getByName(String name);
}
