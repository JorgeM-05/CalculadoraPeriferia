package co.com.ath.calculadora.pruebas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ath.calculadora.pruebas.entity.FabricaEntity;

public interface IFabricaRepository extends JpaRepository<FabricaEntity, Integer> {

	@Query(value = "SELECT fabrica FROM FabricaEntity fabrica WHERE (fabrica.nombreFabrica = :nombreFabrica OR :nombreFabrica IS NULL) AND (fabrica.nombreContacto = :nombreContacto OR :nombreContacto IS NULL)")
	public Page<FabricaEntity> consultarFabricasPorSimillitudes(@Param("nombreFabrica") String nombreFabrica,
			@Param("nombreContacto") String nombreContacto, Pageable page);

	@Query(value = "SELECT fabrica FROM FabricaEntity fabrica WHERE fabrica.dni = :dni")
	public FabricaEntity consultarUnaFabrica( @Param("dni")Integer dni); 

}
