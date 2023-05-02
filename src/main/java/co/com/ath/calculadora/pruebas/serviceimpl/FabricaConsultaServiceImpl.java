package co.com.ath.calculadora.pruebas.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.entity.FabricaEntity;
import co.com.ath.calculadora.pruebas.repository.IFabricaRepository;
import co.com.ath.calculadora.pruebas.service.IFabricaConsultaService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.FabricaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Veronica Jimenez  Clase que realiza la consulta de las fabricas por nombre de la fabrica o nombre de contacto
 */

@Service
@Slf4j
public class FabricaConsultaServiceImpl implements IFabricaConsultaService {

	@Autowired
	private IFabricaRepository fabricaRepository;

	/**
	 * Metodo que la consulta de las fabricas por nombre de la fabrica o nombre de contacto
	 * 
	 * @param nombreFabrica -> para buscar las fabricas
	 *  * @param nombreContacto -> para buscar los nombres de lo contactos
	 *   * @param pag -> para entar a la pagina
	 *    * @param size -> cuantos elementos por pagina 
	 * @return ApiResponseDto
	 */
	@Override
	public ApiResponseDto consultarFabrica(String nombreFabrica, String nombreContacto, Integer pag, Integer size) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		
		Pageable page = PageRequest.of(pag - 1, size);
		Page<FabricaEntity> existeFabrica = fabricaRepository.consultarFabricasPorSimillitudes(nombreFabrica, nombreContacto, page);
		if (existeFabrica.isEmpty())
			return ApiResponseDto.builder().data(null).message(Constants.ERROR_NO_ENCONTRADO)
					.status(HttpStatus.BAD_REQUEST).build();

		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return ApiResponseDto.builder().data(FabricaUtil.listDto(existeFabrica.getContent())).message(Constants.OK)
				.status(HttpStatus.OK).actualPag(pag).totalPag(existeFabrica.getTotalPages()).build();

	}

}
