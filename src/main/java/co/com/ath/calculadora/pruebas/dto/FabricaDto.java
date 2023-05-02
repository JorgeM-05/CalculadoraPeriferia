package co.com.ath.calculadora.pruebas.dto;

import java.util.List;

import lombok.Data;

@Data
public class FabricaDto {

	private Integer dni;
	private String nombreFabrica;
	private String nombreContacto;
	private String telefono;
	private String estado;
	private List<ContactoDto> contactos;
}
