package br.com.ecotarifas.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.ecotarifas.model.entities.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	
	/**
	 * Busca meta total pelo mês
	 * @param mes
	 * @return
	 */
    @Query(value = "SELECT SUM(meta) FROM Equipamento", nativeQuery = true)
    public BigDecimal findMetaTotal();
    
}