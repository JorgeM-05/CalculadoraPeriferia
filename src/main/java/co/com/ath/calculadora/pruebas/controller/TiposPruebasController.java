package co.com.ath.calculadora.pruebas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.TiposPruebasDto;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasCreateService;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasGetAllService;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasGetService;
import co.com.ath.calculadora.pruebas.service.ITiposPruebasUpdateService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ResponseError;
import lombok.extern.slf4j.Slf4j;

/* Cotroller de los tipos de pruebas
 * ABP 2023/04/28
 * ABP 2023/04/29 Ajustes del log
 */
@RestController
@RequestMapping("/tipoprueba")
@Slf4j
public class TiposPruebasController {

	@Autowired
	private ITiposPruebasCreateService createService;
	@Autowired
	private ITiposPruebasGetAllService getAllService;
	@Autowired
	private ITiposPruebasGetService consultaService;
	@Autowired
	private ITiposPruebasUpdateService updateService;
	
	@PostMapping(value = "/crear")
	public ResponseEntity<ApiResponseDto> crear(@RequestBody TiposPruebasDto tiposPruebasDto) {
		/* Ajustes controler crear
		 * ABP 2023/04/30 y 2023/04/30
		 */
		ApiResponseDto response;
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			response = createService.createTipoPrueba(tiposPruebasDto);
		} catch (Exception e) {
			log.error("Ha ocurrido un error en la creacion de tipos pruebas {}", e.getMessage());
			response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
		}
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@PutMapping(value = "/actualizar")
	public ResponseEntity<ApiResponseDto> actualizar(@RequestBody TiposPruebasDto tiposPruebasDto){
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		ApiResponseDto response;
		try {
			response = updateService.updateTipoPrueba(tiposPruebasDto);
		} catch (Exception e) {
			log.error("Ha ocurrido un error en la actualizacion de tipos pruebas {}", e.getMessage());
			response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
		}
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@GetMapping("/todos")
	public ResponseEntity<ApiResponseDto> todos() {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		ApiResponseDto response;
		try {
			response = getAllService.getAllTipoPrueba();
		} catch (Exception e) {
			log.error("Ha ocurriod un error al leer todos los tipos de pruebas {}", e.getMessage());
			response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
		}
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@GetMapping("/consultar")
	public ResponseEntity<ApiResponseDto> consultar(String nombre, String descripcion, int pag, int size) {
		log.info(Constants.LOG_IN, Thread.currentThread().getStackTrace()[1].getMethodName());
		ApiResponseDto response;
		try {
			response = consultaService.getTipoPrueba(nombre, descripcion, null, null);
		} catch (Exception e) {
			log.error("Ha ocurriod un error al consultar los tipos de pruebas {}", e.getMessage());
			response = ResponseError.error(Constants.ERROR_NO_ENCONTRADO);
		}
		log.info(Constants.LOG_OUT, Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<>(response, response.getStatus());
	}
}
