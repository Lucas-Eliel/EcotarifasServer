package br.com.ecotarifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ecotarifas.model.entities.Bandeiras;

@Repository
public interface BandeirasRepository extends JpaRepository<Bandeiras, Long> {}
