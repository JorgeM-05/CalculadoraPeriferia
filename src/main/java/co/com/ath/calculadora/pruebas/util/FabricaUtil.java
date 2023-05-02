package co.com.ath.calculadora.pruebas.util;

import java.util.ArrayList;
import java.util.List;

import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;

public class FabricaUtil {

	private FabricaUtil() {}
	
	public static FabricaEntity dtoToEntity(FabricaDto dto, FabricaEntity entity) {
		entity.setNombreFabrica(dto.getNombreFabrica());
		entity.setNombreContacto(dto.getNombreContacto());
		entity.setTelefono(dto.getTelefono());
		entity.setEstado(dto.getEstado());
		return entity;
	}

	public static FabricaDto entityToDto(FabricaDto dto, FabricaEntity entity) {
		dto.setDni(entity.getDni());
		dto.setNombreFabrica(entity.getNombreFabrica());
		dto.setNombreContacto(entity.getNombreContacto());
		dto.setTelefono(entity.getTelefono());
		dto.setEstado(entity.getEstado());
		return dto;
	}

	public static List<FabricaDto> listDto(List<FabricaEntity> entityFrabrica) {
		List<FabricaDto> dto = new ArrayList<>();
		entityFrabrica.stream().forEach(toDto -> {
			
			FabricaDto dtoNew = new FabricaDto();
			dto.add(entityToDto(dtoNew, toDto));
		});
		return dto;
	}

}
