package co.com.ath.calculadora.pruebas.serviceimpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.ContactoDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.entity.ContactoEntity;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IContactoRepository;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.service.IFabricaActualizarService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ContactoUtil;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veronica Jimenez Clase que actualiza la fabrica con sus contactos
 */

@Service
@Slf4j
public class FabricaActualizarServiceImpl implements IFabricaActualizarService {

	@Autowired
	private IFabricaRepository fabricaRepository;

	@Autowired
	private IContactoRepository contactoRepository;

	/**
	 * Metodo que actualiza la fabrica con sus contactos
	 * 
	 * @param fabricaDTO -> objeto con los datos de la fabrica y sus contactos
	 *                   (nombreFabrica, nombreContacto, telefono, estado,
	 *                   contactos)
	 * @return ApiResponseDto
	 */
	@Override
	public ApiResponseDto actualizarFabrica(FabricaDto fabricaDto) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());

		FabricaEntity existeFabrica = fabricaRepository.consultarUnaFabrica(fabricaDto.getDni());

		if (existeFabrica == null)
			return ApiResponseDto.builder().data(null).message(Constants.ERROR_NO_ENCONTRADO)
					.status(HttpStatus.BAD_REQUEST).build();

		fabricaRepository.save(FabricaUtil.dtoToEntity(fabricaDto, existeFabrica));

		validacionActualizar(fabricaDto);
		agregarContactos(fabricaDto);

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder().data(null).message(Constants.OK).status(HttpStatus.OK).build();
	}

	/**
	 * Metodo que validad los contactos existentes en la base de datos contra los
	 * contactos que resibimos desde el dto
	 * 
	 * @param fabricaDTO -> objeto con los datos de la fabrica y sus contactos
	 *                   (nombreFabrica, nombreContacto, telefono, estado,
	 *                   contactos)
	 * @return ApiResponseDto
	 */
	public void validacionActualizar(FabricaDto fabricaDto) {

		boolean noCoinside = false;
		List<ContactoEntity> contactosExistentes = contactoRepository.consultarContactosPorFabrica(fabricaDto.getDni());
		for (ContactoEntity contactoEntity : contactosExistentes) {
			for (ContactoDto contactoDto : fabricaDto.getContactos()) {
				if (Objects.equals(contactoEntity.getDni(), contactoDto.getDni())) {
					contactoDto.setDniFabrica(fabricaDto.getDni());
					contactoRepository.save(ContactoUtil.dtoToEntity(contactoDto, contactoEntity));
					noCoinside = true;
				}
			}
			if (!noCoinside)
				contactoRepository.deleteById(contactoEntity.getDni());
		}
	}

	/**
	 * Metodo que validad si en el dto alguno de los contactos tiene un dni 0 lo
	 * cual significara que es un contacto para crear en la base de datos
	 * 
	 * @param fabricaDTO -> objeto con los datos de la fabrica y sus contactos
	 *                   (nombreFabrica, nombreContacto, telefono, estado,
	 *                   contactos)
	 * @return ApiResponseDto
	 */
	public void agregarContactos(FabricaDto fabricaDto) {
		for (ContactoDto contactoDto : fabricaDto.getContactos()) {
			if (contactoDto.getDni() == 0)
				contactoRepository.save(ContactoUtil.contactoEntity(fabricaDto.getDni(), contactoDto));
		}
	}
}
