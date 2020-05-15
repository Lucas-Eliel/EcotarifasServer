package br.com.ecotarifas.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ecotarifas.model.entities.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
	
	/**
	 * Busca as medidas elétricas salvas conforme um periodo de datas
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
    @Query(value = "SELECT * FROM Consumo WHERE data > :dataInicial and data < :dataFinal ", nativeQuery = true)
    public List<Consumo> findConsumoPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
	
	/**
	 * Busca consumo total pelo mês
	 * @param mes
	 * @return
	 */
    @Query(value = "SELECT SUM(total) FROM Consumo WHERE DATE_PART('MONTH', data) = :mes", nativeQuery = true)
    public BigDecimal findConsumoTotal(@Param("mes") BigDecimal mes);
 
	/**
	 * Busca dados para o grafico
	 * @param mes
	 * @return
	 */
    @Query(value = "SELECT NOME, \n" + 
			    	   "(SELECT CASE WHEN SUM(TOTAL) IS NULL THEN (SELECT 0) ELSE SUM(TOTAL) END FROM CONSUMO WHERE DATE_PART('MONTH', data) = :mes AND DATE_PART('YEAR', data) = :ano AND DATE_PART('DAY', data) BETWEEN '1' AND '7' AND NOME = :nome) AS SEMANA1, \n" + 
			    	   "(SELECT CASE WHEN SUM(TOTAL) IS NULL THEN (SELECT 0) ELSE SUM(TOTAL) END FROM CONSUMO WHERE DATE_PART('MONTH', data) = :mes AND DATE_PART('YEAR', data) = :ano AND DATE_PART('DAY', data) BETWEEN '7' AND '14' AND NOME = :nome) AS SEMANA2, \n" + 
			    	   "(SELECT CASE WHEN SUM(TOTAL) IS NULL THEN (SELECT 0) ELSE SUM(TOTAL) END FROM CONSUMO WHERE DATE_PART('MONTH', data) = :mes AND DATE_PART('YEAR', data) = :ano AND DATE_PART('DAY', data) BETWEEN '14' AND '21' AND NOME = :nome) AS SEMANA3, \n" + 
			    	   "(SELECT CASE WHEN SUM(TOTAL) IS NULL THEN (SELECT 0) ELSE SUM(TOTAL) END FROM CONSUMO WHERE DATE_PART('MONTH', data) = :mes AND DATE_PART('YEAR', data) = :ano AND DATE_PART('DAY', data) BETWEEN '21' AND '30' AND NOME = :nome) AS SEMANA4 \n" +
			    	   "FROM CONSUMO \n" + 
		    	   "WHERE NOME = :nome GROUP BY NOME", nativeQuery = true)
    public Object findDadosGrafico(@Param("mes") BigDecimal mes, @Param("ano") BigDecimal ano, @Param("nome") String nome);

	/**
	 * Nome das Cargas no periodo
	 * @param mes
	 * @return
	 */
    @Query(value = "SELECT NOME FROM CONSUMO  \n" + 
		    	   "WHERE DATE_PART('MONTH', data) = :mes AND DATE_PART('YEAR', data) = :ano GROUP BY NOME ", nativeQuery = true)
    public List<Object> findNomeCargas(@Param("mes") BigDecimal mes, @Param("ano") BigDecimal ano);

}