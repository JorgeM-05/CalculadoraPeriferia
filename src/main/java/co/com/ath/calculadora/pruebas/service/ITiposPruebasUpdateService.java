package co.com.ath.calculadora.pruebas.service;
/* Actualizar en la BD tipos de pruebas
 * ABP 2023/04/27
 */

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;

public interface ITiposPruebasUpdateService {
	
	public ApiResponseDto updateTipoPrueba(TiposPruebasDto tipoPruebaDto);
}
