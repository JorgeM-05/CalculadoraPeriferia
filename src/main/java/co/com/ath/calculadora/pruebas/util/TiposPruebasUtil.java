package co.com.ath.calculadora.pruebas.util;

import java.util.ArrayList;
import java.util.List;

import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;
import co.com.ath.calculadora.pruebas.entity.TiposPruebasEntity;

/* Utilidades de tipos de pruebas
 * ABP 2023/04/27
 */

public class TiposPruebasUtil {
	/* Ajustes en la BD los tipos de pruebas
	 * ABP 2023/04/29
	 */

	/* 
	 * Convierte TiposPruebasDto a TiposPruebasEntity 
	 */
	public static TiposPruebasEntity dtoToEntity (TiposPruebasDto dto) {
		TiposPruebasEntity entity = new TiposPruebasEntity();
		entity.setDni(dto.getDni());
		entity.setNombre(dto.getNombre());
		entity.setDescripcion(dto.getDescripcion());
		entity.setResponsable(dto.getResponsable());
		entity.setCorreoResponsable(dto.getCorreoResponsable());
		entity.setEstado(dto.getEstado());
		return entity;
	}
	/* 
	 * Convierte TiposPruebasEntity a TiposPruebasDto
	 */
	public static TiposPruebasDto entityToDto (TiposPruebasDto dto, TiposPruebasEntity entity ) {
		dto.setDni(entity.getDni());
		dto.setNombre(entity.getNombre());
		dto.setDescripcion(entity.getDescripcion());
		dto.setResponsable(entity.getResponsable());
		dto.setCorreoResponsable(entity.getCorreoResponsable());
		dto.setEstado(entity.getEstado());
		return dto;
	}
	/* 
	 * Crea una lista de TiposPruebasDto
	 */
	public static List<TiposPruebasDto> listDto(List<TiposPruebasEntity> entity) {
		List<TiposPruebasDto> dto = new ArrayList<>();
		entity.stream().forEach(toDto -> {
			TiposPruebasDto dtoNew = new TiposPruebasDto();
			dto.add(entityToDto(dtoNew, toDto));
		});
		return dto;
	}
}
