package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersActualizarService;
import co.com.ath.calculadora.pruebas.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParametersActualizarServiceImpl implements IParametersActualizarService {

    @Autowired
    private ParametersRepository parametersRepository;
    @Override
    public ApiResponseDto updateParameters(int dni, RequestParametersDto requestParametersDto) {
        ParametersEntity parametersEntity = parametersRepository.findByDni(dni);
        log.info("param update <{}>", parametersEntity);
        if (parametersEntity != null) {
            parametersEntity.setCapa(requestParametersDto.getCapa());
            parametersEntity.setEstado(requestParametersDto.getEstado());
            parametersEntity.setValor(requestParametersDto.getValor());
            parametersEntity= parametersRepository.save(parametersEntity);
            log.info("param update <{}>",parametersEntity);

            if (parametersEntity.getDni() > 0) {
                return ApiResponseDto.builder().data(parametersEntity).message(Constants.SUCESSFULL).status(HttpStatus.CREATED).build();
            }
        }
        return ApiResponseDto.builder().data(null).message("Error actualizando en Base Datos").status(HttpStatus.CONFLICT).build();
    }
}
