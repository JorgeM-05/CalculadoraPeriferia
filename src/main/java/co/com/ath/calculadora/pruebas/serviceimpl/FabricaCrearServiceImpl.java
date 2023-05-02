package co.com.ath.calculadora.pruebas.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.service.IFabricaCrearService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veronica Jimenez Clase que realiza la creacion de una nueva fabrica con sus contactos
 */

@Service
@Slf4j
public class FabricaCrearServiceImpl implements IFabricaCrearService {

	@Autowired
	private IFabricaRepository fabricaRepository;

	@Autowired
	private IContactoRepository contactoRepository;

	/**
	 * Metodo que crear la fabrica con sus contactos
	 * 
	 * @param fabricaDTO -> objeto con los datos de la fabrica y sus contactos (nombreFabrica, nombreContacto, telefono, estado, contactos)
	 * @return ApiResponseDto
	 */
	@Override
	public ApiResponseDto crearFabrica(FabricaDto fabricaDto) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		
		FabricaEntity crearFabrica = fabricaRepository.save(FabricaUtil.dtoToEntity(fabricaDto, new FabricaEntity()));

		List<ContactoEntity> contactos = new ArrayList<>();		
		fabricaDto.getContactos().stream().forEach(contactoDto -> {
			ContactoEntity crearContacto = contactoRepository.save(ContactoUtil.contactoEntity(crearFabrica.getDni(), contactoDto));
			contactos.add(crearContacto);
		});
		
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder().data(null).message(Constants.OK).status(HttpStatus.OK).build();
	}
}
