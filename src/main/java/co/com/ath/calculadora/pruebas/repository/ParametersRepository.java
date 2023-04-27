package co.com.ath.calculadora.pruebas.repository;

import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametersRepository extends PagingAndSortingRepository<ParametersEntity, Integer>{

    Page<ParametersEntity> findByCapa(String capa, Pageable pageable);
    Page<ParametersEntity> findByValor(String valor, Pageable pageable);
    ParametersEntity findByDni(int dni);
}