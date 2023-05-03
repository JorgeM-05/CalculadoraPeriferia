package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ParametroUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ParametersCrearServiceImplTest {
    private ParametersEntity parametersEntity;
    private RequestParametersDto parametersDto;

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private ParametersCrearServiceImpl parametersCrearService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        parametersDto = new RequestParametersDto();
        parametersDto.setCapa("capa");
        parametersDto.setEstado("activo");
        parametersDto.setValor("valor");

        parametersEntity = ParametroUtil.mapParametersEntity(parametersDto);
    }

    @Test
    void testParametroCrearServiceImpl() {
        Mockito.when(parametersRepository.save(parametersEntity)).thenReturn(parametersEntity);

        ApiResponseDto expected = ApiResponseDto.builder().data(parametersEntity)
                    .message(Constants.SUCESSFULL)
                    .status(HttpStatus.CREATED).build();
        ApiResponseDto resul = parametersCrearService.createParameters(parametersDto);

        assertEquals(expected, resul);
    }


}
