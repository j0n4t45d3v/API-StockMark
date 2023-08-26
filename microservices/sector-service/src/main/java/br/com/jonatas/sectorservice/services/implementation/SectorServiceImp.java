package br.com.jonatas.sectorservice.services.implementation;

import br.com.jonatas.sectorservice.error.exceptions.ConflictException;
import br.com.jonatas.sectorservice.error.exceptions.NotFoundException;
import br.com.jonatas.sectorservice.models.Product;
import br.com.jonatas.sectorservice.models.Sector;
import br.com.jonatas.sectorservice.repositories.SectorRepository;
import br.com.jonatas.sectorservice.services.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorServiceImp implements SectorService {

    private final SectorRepository sectorRepository;
    @Override
    public String save(Sector sector) {
        this.sectorRepository.findByName(sector.getName())
                .ifPresent(s -> {
                    throw new ConflictException("Setor já cadastrado!");
                });
        this.sectorRepository.save(sector);
        return "Setor criado!";
    }

    @Override
    public Sector getByName(String name) {
        return this.sectorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Setor não encontrado!"));
    }

    @Override
    public String addProduct(String sectorName, Product product) {
        Sector sectorAlreadyExists = this.sectorRepository.findByName(sectorName)
                .orElseThrow(() -> new NotFoundException("Setor não encontrado!"));

        product.setSector(sectorAlreadyExists);
        sectorAlreadyExists.getProducts().add(product);
        this.sectorRepository.save(sectorAlreadyExists);

        return "Produto adicionado!";
    }

    @Override
    public String deleteSector(String sectorName) {
        Sector sectorAlreadyExists = this.sectorRepository.findByName(sectorName)
                .orElseThrow(() -> new NotFoundException("Setor não encontrado!"));
        this.sectorRepository.delete(sectorAlreadyExists);
        return "Setor deletado!";
    }
}
