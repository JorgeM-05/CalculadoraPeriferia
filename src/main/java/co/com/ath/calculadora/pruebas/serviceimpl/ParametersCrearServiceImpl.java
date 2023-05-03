package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.service.IParametersCrearService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ParametroUtil;
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

        ParametersEntity parametersEntity = null;
        if (!requestParametersDto.getCapa().isEmpty() && !requestParametersDto.getValor().isEmpty() && !requestParametersDto.getEstado().isEmpty())
            parametersEntity = parametersRepository.save(ParametroUtil.mapParametersEntity(requestParametersDto));
        if (parametersEntity!=null) {
            return ApiResponseDto.builder().data(parametersEntity).message(Constants.SUCESSFULL).status(HttpStatus.CREATED).build();
        }
        return ApiResponseDto.builder().data(null).message("Error insertando en Base Datos").status(HttpStatus.CONFLICT).build();
    }


}
