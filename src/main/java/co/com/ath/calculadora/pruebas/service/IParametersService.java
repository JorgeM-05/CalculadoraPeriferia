package co.com.ath.calculadora.pruebas.service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;

public interface IParametersService {
    public ApiResponseDto getParametersByValue(String valor, Integer pageNo, Integer pageSize);
    public ApiResponseDto getParametersByLayer(String capa, Integer pageNo, Integer pageSize);

    public ApiResponseDto createParameters(RequestParametersDto requestParametersDto);

}
