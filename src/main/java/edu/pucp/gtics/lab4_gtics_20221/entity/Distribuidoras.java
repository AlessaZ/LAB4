package edu.pucp.gtics.lab4_gtics_20221.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddistribuidora")
    private int iddistribuidora;

    @Size(min=3,max=50, message = "Debe contener entre 3 y 50 caracteres")
    @Column(name="nombre")
    private String nombre;

    @Size(min=3,max=198, message = "Debe contener entre 3 y 198 caracteres")
    @Column(name="descripcion")
    private String descripcion;

    @URL(message = "Debe ingresar una url")
    @Size(min=3,max=198, message = "Debe contener entre 3 y 198 caracteres")
    @Column(name="web")
    private String web;

    @Digits(integer=4,fraction=0)
    @Min(value = 1800, message = "Debe ser mayor o igual que 1800")
    @Max(value = 2100, message = "Debe ser menor o igual que 2100")
    @Column(name="fundacion")
    private int fundacion=1870;

    @NotNull(message = "Sede no puede estar vacío")
    @ManyToOne
    @JoinColumn(name = "idsede")
    private Paises pais;

    public int getIddistribuidora() {
        return iddistribuidora;
    }

    public void setIddistribuidora(int iddistribuidora) {
        this.iddistribuidora = iddistribuidora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }
}
