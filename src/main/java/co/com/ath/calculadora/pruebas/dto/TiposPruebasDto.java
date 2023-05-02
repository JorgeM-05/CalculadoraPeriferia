package co.com.ath.calculadora.pruebas.dto;
import lombok.Data;
/* tipos de pruebas DTO
 * ABP 2023/04/26
 */
@Data
public class TiposPruebasDto {
	/* Ajustes a los campos
	 * ABP 2023/04/29
	 */
	
	private Integer dni;
	private String nombre;
	private String descripcion;
	private String responsable;
	private String correoResponsable;
	private String estado;
}
