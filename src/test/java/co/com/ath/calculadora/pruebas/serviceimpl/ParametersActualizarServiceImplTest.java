package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.dto.RequestParametersDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class ParametersActualizarServiceImplTest {

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private ParametersActualizarServiceImpl parametrosActualizar;

    private RequestParametersDto parametersDto;
    private ParametersEntity parametersEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        parametersDto = new RequestParametersDto();
        parametersDto.setCapa("capa1");
        parametersDto.setEstado("estado");
        parametersDto.setValor("valor");
        parametersEntity = new ParametersEntity();
        parametersEntity.setDni(1);
        parametersEntity.setCapa("capa1");
        parametersEntity.setEstado("estado");
        parametersEntity.setValor("valor");

    }

    @Test
    void testUpdateParameters() {

        Mockito.when(parametersRepository.findByDni(parametersEntity.getDni())).thenReturn(parametersEntity);
        Mockito.when(parametersRepository.save(parametersEntity)).thenReturn(parametersEntity);

        ApiResponseDto expected = ApiResponseDto.builder().data(parametersEntity)
                .message(Constants.SUCESSFULL)
                .status(HttpStatus.CREATED).build();

        ApiResponseDto resul = parametrosActualizar.updateParameters(parametersEntity.getDni(), parametersDto);
        assertEquals(expected, resul);


//        ApiResponseDto expected = ApiResponseDto.builder().data(null).message("Error actualizando en Base Datos").status(HttpStatus.CONFLICT).build();
    }
}
