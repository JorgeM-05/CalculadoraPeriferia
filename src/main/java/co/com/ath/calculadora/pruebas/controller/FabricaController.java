package co.com.ath.calculadora.pruebas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ath.calculadora.pruebas.dto.ApiResponseDto;
import co.com.ath.calculadora.pruebas.dto.FabricaDto;
import co.com.ath.calculadora.pruebas.service.IFabricaActualizarService;
import co.com.ath.calculadora.pruebas.service.IFabricaCrearService;
import co.com.ath.calculadora.pruebas.service.IFabricaTodosService;
import co.com.ath.calculadora.pruebas.service.IFabricaUnRegistroService;
import co.com.ath.calculadora.pruebas.service.IFabricaConsultaService;
import co.com.ath.calculadora.pruebas.util.Constants;
import co.com.ath.calculadora.pruebas.util.ResponseError;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fabrica")
@Slf4j
public class FabricaController {

	@Autowired
	private IFabricaActualizarService actualizarService;

	@Autowired
	private IFabricaCrearService crearService;

	@Autowired
	private IFabricaConsultaService consultarService;

	@Autowired
	private IFabricaTodosService todosService;
	

	@Autowired
	private IFabricaUnRegistroService unRegistroService;

	@PostMapping(value = "/crear")
	public ResponseEntity<ApiResponseDto> crear(@RequestBody FabricaDto fabricaDto) {
		ApiResponseDto respuesta;
		try {
			respuesta = crearService.crearFabrica(fabricaDto);
		} catch (Exception e) {
			log.error("Ha ocurrido un error en la creacion de la fabrica {}", e.getMessage());
			respuesta = ResponseError.error(Constants.ERROR_GENERAL);
		}
		return new ResponseEntity<>(respuesta, respuesta.getStatus());
	}

	@PutMapping(value = "/actualizar")
	ResponseEntity<ApiResponseDto> actualizar(
			@RequestBody FabricaDto fabricaDto) {
		ApiResponseDto respueta;
		try {
			respueta = actualizarService.actualizarFabrica(fabricaDto);
		} catch (Exception e) {
			log.error("Ha ocurrido un error en la actualizacion de la fabrica {}", e.getMessage());
			respueta = ResponseError.error(Constants.ERROR_GENERAL);
		}
		return new ResponseEntity<>(respueta, respueta.getStatus());
	}

	@GetMapping("/todos")
	public ResponseEntity<ApiResponseDto> todos() {
		ApiResponseDto respueta;
		try {
			respueta = todosService.todosFabrica();
		} catch (Exception e) {
			log.error("Ha ocurrido un error al consultar todas las fabricas {}", e.getMessage());
			respueta = ResponseError.error(Constants.ERROR_GENERAL);
		}
		return new ResponseEntity<>(respueta, respueta.getStatus());
	}

	@GetMapping("/consultar")
	public ResponseEntity<ApiResponseDto> consultar(
			@RequestHeader(value = Constants.X_NOMBRE_FABRICA, required = false) String nombreFabrica,
			@RequestHeader(value = Constants.X_NOMBRE_CONTACTO, required = false) String nombreContacto,
			@RequestHeader(value = Constants.X_PAG, required = true) Integer pag,
			@RequestHeader(value = Constants.X_SIZE, required = true) Integer size) {
		ApiResponseDto repuesta;
		try {
			repuesta = consultarService.consultarFabrica(nombreFabrica, nombreContacto, pag, size);
		} catch (Exception e) {
			log.error("Ha ocurrido un error al consultar las fabricas {}", e.getMessage());
			repuesta = ResponseError.error(Constants.ERROR_GENERAL);
		}
		return new ResponseEntity<>(repuesta, repuesta.getStatus());
	}
	
	@GetMapping("/unRegistro")
	public ResponseEntity<ApiResponseDto> unRegistro(
			@RequestHeader(value = Constants.X_DNI_FABRICA, required = true) Integer dni) {
		ApiResponseDto respueta;
		try {
			respueta = unRegistroService.unaFabrica(dni);
		} catch (Exception e) {
			log.error("Ha ocurrido un error al encontar la fabrica {}", e.getMessage());
			respueta = ResponseError.error(Constants.ERROR_GENERAL);
		}
		return new ResponseEntity<>(respueta, respueta.getStatus());
	}
}
