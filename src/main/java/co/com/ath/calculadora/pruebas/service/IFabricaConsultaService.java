package co.com.ath.calculadora.pruebas.service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;

public interface IFabricaConsultaService {

	public ApiResponseDto consultarFabrica(String nombreFabrica, String nombreContacto, Integer pag, Integer size);
}
