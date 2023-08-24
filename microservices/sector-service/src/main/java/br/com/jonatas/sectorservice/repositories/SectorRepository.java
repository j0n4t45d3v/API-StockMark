package br.com.jonatas.sectorservice.repositories;

import br.com.jonatas.sectorservice.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SectorRepository extends JpaRepository<Sector, UUID> {
    Optional<Sector> findByName(String name);
}
