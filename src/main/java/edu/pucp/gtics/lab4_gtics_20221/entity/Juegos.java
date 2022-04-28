package edu.pucp.gtics.lab4_gtics_20221.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "juegos")
public class Juegos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private int id;

    @Column(name = "nombre", length = 50)
    @Size(min=3,max=45, message = "Debe contener como entre 3 y 45 caracteres")
    private String nombre;

    @Column(name = "descripcion", length = 448)
    @Size(min=3, max=400, message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;

    @Column(name = "precio")
    @Min(value=10, message = "Valor mínimo 10")
    @Max(value = 500, message = "Valor máximo 500")
    private Double precio=0.0;

    @Column(name = "image", length = 400)
    @URL(message = "Debe ingresar una url.")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgenero")
    @NotNull(message = "Género no puede estar vacío")
    private Generos genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idplataforma")
    @NotNull(message = "Plataforma no puede estar vacío.")
    private Plataformas plataforma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ideditora")
    private Editora editora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddistribuidora")
    @NotNull(message = "Distribuidora no puede estar vacío.")
    private Distribuidoras distribuidora;

}