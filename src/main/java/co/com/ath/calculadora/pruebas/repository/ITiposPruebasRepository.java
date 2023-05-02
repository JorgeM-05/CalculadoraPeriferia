package co.com.ath.calculadora.pruebas.repository;
import java.util.List;

/* Repositorio de tipos de pruebas
 * ABP 2023/04/26
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ath.calculadora.pruebas.entity.TiposPruebasEntity;
/* tipos de pruebas repository
 * ABP 2023/04/26
 */
public interface ITiposPruebasRepository extends JpaRepository<TiposPruebasEntity, Integer> {
	/* Definicion de consulta
	 * ABP 2023/04/27
	 * ABP 2023/04/29 Ajustes 
	 */
	@Query(value = "SELECT TPruebas FROM TiposPruebasEntity TPruebas WHERE (TPruebas.nombre = :nombreTP OR :TPruebas IS NULL) AND (TPruebas.descripcion = :descripcionTP OR :descripcionTP IS NULL)")
	public List<TiposPruebasEntity> findOneTiposPruebas(@Param("nombre") String nombreTP, @Param("descripcion") String descripcionTP);

}
