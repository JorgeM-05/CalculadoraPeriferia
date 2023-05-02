package co.com.ath.calculadora.pruebas.serviceimpl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;

class FabricaCrearServiceImplTest {

	@Mock
	private IFabricaRepository fabricaRepository;

	@Mock
	private IContactoRepository contactoRepository;
	
	@InjectMocks
	private FabricaCrearServiceImpl fabricaCrearServiceImpl;
	
	private FabricaDto fabricaDto;
	private ContactoDto contactoDto;
	private FabricaEntity fabricaEntity;
	private ContactoEntity contactoEntity;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
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
		
		fabricaDto.setContactos(List.of(contactoDto));
		
		fabricaEntity = new FabricaEntity();
		FabricaUtil.dtoToEntity(fabricaDto, fabricaEntity);
		    
		contactoEntity = new ContactoEntity();
		ContactoUtil.dtoToEntity(contactoDto, contactoEntity);
	}
	
	@Test
	void testFabricaCrearServiceImpl() {
		when(fabricaRepository.save(fabricaEntity)).thenReturn(fabricaEntity);
		when(contactoRepository.save(contactoEntity)).thenReturn(contactoEntity);
			
		ApiResponseDto expected = ApiResponseDto.builder().data(null).message(Constants.OK).status(HttpStatus.OK).build();
		
		ApiResponseDto resul = fabricaCrearServiceImpl.crearFabrica(fabricaDto);
		
		assertEquals(expected, resul);
	}
}
