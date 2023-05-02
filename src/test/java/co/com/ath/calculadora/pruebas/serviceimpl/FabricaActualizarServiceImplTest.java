package co.com.ath.calculadora.pruebas.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;

class FabricaActualizarServiceImplTest {

	@Mock
	private IFabricaRepository fabricaRepository;

	@Mock
	private IContactoRepository contactoRepository;
	
	@InjectMocks
	private FabricaActualizarServiceImpl fabricaActualizarServiceImpl;
	

	private FabricaDto fabricaDto;
	private ContactoDto contactoDto;
	private FabricaEntity fabricaEntity;
	private ContactoEntity contactoEntity;
	
	@BeforeEach
	void setUp(){
		fabricaDto = new FabricaDto();
		fabricaDto.setDni(1);
		fabricaDto.setNombreFabrica("ath");
		fabricaDto.setNombreContacto("veronica");
		fabricaDto.setTelefono("1001");
		fabricaDto.setEstado("activo");
		
		contactoDto = new ContactoDto();
		contactoDto.setDni(1);
		contactoDto.setNombre("Sofia");
		contactoDto.setCorreo("sofi@gmail");
		contactoDto.setCargo("aprendiz");
		
		fabricaEntity = new FabricaEntity();
		FabricaUtil.dtoToEntity(fabricaDto, fabricaEntity);
		    
		contactoEntity = new ContactoEntity();
		ContactoUtil.dtoToEntity(contactoDto, contactoEntity);
		
	}
	
	@Test
	void testFabricaActualizarServiceImpl() {
		when(fabricaRepository.consultarUnaFabrica(fabricaDto.getDni())).thenReturn(fabricaEntity);
		when(fabricaRepository.save(fabricaEntity)).thenReturn(fabricaEntity);

		when(contactoRepository.consultarContactosPorFabrica(fabricaDto.getDni())).thenReturn(List.of(contactoEntity));
		when(contactoRepository.save(contactoEntity)).thenReturn(contactoEntity);
		//when(contactoRepository.deleteById(contactoEntity.getDni()));
		
		when(contactoRepository.save(ContactoUtil.contactoEntity(fabricaDto.getDni(), contactoDto))).thenReturn(contactoEntity);
		
		
		ApiResponseDto expected = ApiResponseDto.builder().data(null).message(Constants.OK).status(HttpStatus.OK).build();
		
		ApiResponseDto resul = fabricaActualizarServiceImpl.actualizarFabrica(fabricaDto);
		
		assertEquals(expected, resul);

	}
	
}
