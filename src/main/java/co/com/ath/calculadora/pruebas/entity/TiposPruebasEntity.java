package co.com.ath.calculadora.pruebas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/* Entidad de tipos de pruebas
 * ABP 2023/04/26
 */
@Data
@Entity
@Table(name = "tipos_pruebas")
public class TiposPruebasEntity {
	/* Ajustes en la BD los tipos de pruebas
	 * ABP 2023/04/29
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dni;
	
	private String nombre;
	
	private String descripcion;
	
	private String responsable;
	
	@Column(name="correo_responsable")
	private String correoResponsable;
	
	private String estado;
}
