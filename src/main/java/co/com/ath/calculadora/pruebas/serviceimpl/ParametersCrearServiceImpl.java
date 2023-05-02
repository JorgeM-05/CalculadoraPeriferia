package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersCrearService;
import co.com.ath.calculadora.pruebas.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParametersCrearServiceImpl implements IParametersCrearService {

    @Autowired
    private ParametersRepository parametersRepository;
    @Override
    public ApiResponseDto createParameters(RequestParametersDto requestParametersDto) {

        ParametersEntity parametersEntity = new ParametersEntity();
        if (!requestParametersDto.getCapa().isEmpty() && !requestParametersDto.getValor().isEmpty() && !requestParametersDto.getEstado().isEmpty() && !requestParametersDto.getCapa().isEmpty())
            parametersEntity = parametersRepository.save(mapParametersEntity(requestParametersDto));

        if (parametersEntity.getDni() > 0) {
            return ApiResponseDto.builder().data(parametersEntity).message(Constants.SUCESSFULL).status(HttpStatus.CREATED).build();
        }
        return ApiResponseDto.builder().data(null).message("Error insertando en Base Datos").status(HttpStatus.CONFLICT).build();
    }
    private ParametersEntity mapParametersEntity(RequestParametersDto requestParametersDto) {
        ParametersEntity parametersEntity = new ParametersEntity();

        if (requestParametersDto != null) {
            parametersEntity.setCapa(requestParametersDto.getCapa());
            parametersEntity.setEstado(requestParametersDto.getEstado());
            parametersEntity.setValor(requestParametersDto.getValor());
        }
        return parametersEntity;
    }
}
