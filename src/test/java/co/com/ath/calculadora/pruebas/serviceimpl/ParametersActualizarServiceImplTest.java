package co.com.ath.calculadora.pruebas.serviceimpl;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ParametersDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.entity.ParametersEntity;
import co.com.ath.calculadora.pruebas.repository.ParametersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.when;


class ParametersActualizarServiceImplTest {

    @Mock
    private ParametersRepository parametersRepository;

    @InjectMocks
    private ParametersActualizarServiceImpl parametrosActualizar;

    private ParametersDto parametersDto;
    private ParametersEntity parametersEntity;

    @BeforeEach
        void setUp (){
        parametersDto = new ParametersDto();
        parametersDto.setDni(123);
        parametersDto.setCapa("capa1");
        parametersDto.setEstado("estado");
        parametersDto.setValor("valor");

        parametersEntity = new ParametersEntity();

    }
    @Test
    void testUpdateParameters(){
        int dni = 0;
//        when(parametersRepository.findByDni(dni)).thenReturn(List.of(parametrosEntity));

//        ApiResponseDto expected = ApiResponseDto.builder().data(null).message("Error actualizando en Base Datos").status(HttpStatus.CONFLICT).build();
    }
}
