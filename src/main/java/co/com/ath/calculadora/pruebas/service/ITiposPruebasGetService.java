package co.com.ath.calculadora.pruebas.service;
/* Filtar los tipos de pruebas
 * ABP 2023/04/27
 */

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;

public interface ITiposPruebasGetService {

	public ApiResponseDto getTipoPrueba(String nombreTipo, String descripcionTipo, Integer pag, Integer size);
}
