package co.com.ath.calculadora.pruebas.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "contextualizacion")
@Data
public class ContextualizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dni;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    @Column(name = "autor")
    private Integer autor;

    @Column(name = "dni_fabrica")
    private Integer dniFabrica;

    @Column(name = "gestor")
    private String gestor;

}
