package co.com.ath.calculadora.pruebas.service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;

public interface IParametersActualizarService {
    public ApiResponseDto updateParameters(int dni, RequestParametersDto requestParametersDto);
}
