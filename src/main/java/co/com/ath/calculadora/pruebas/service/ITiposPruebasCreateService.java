package co.com.ath.calculadora.pruebas.service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;

public interface ITiposPruebasCreateService {

	public ApiResponseDto createTipoPrueba(TiposPruebasDto tipoPruebaDto);
}
