package co.com.ath.calculadora.pruebas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ath.calculadora.pruebas.entity.ContactoEntity;

public interface IContactoRepository extends JpaRepository<ContactoEntity, Integer>{
		
	@Query(value = "SELECT contacto FROM ContactoEntity contacto WHERE contacto.dniFabrica = :dniFabrica")
	public List<ContactoEntity> consultarContactosPorFabrica(@Param("dniFabrica") Integer dniFabrica);
	
}

