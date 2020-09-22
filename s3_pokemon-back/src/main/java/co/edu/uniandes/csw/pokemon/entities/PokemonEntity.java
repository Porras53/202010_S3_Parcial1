/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Manuel Porras
 */
@Entity
public class PokemonEntity  extends BaseEntity implements Serializable{
    
    private String nombre;
    private String descripcion;
    private Double peso;
    private Double altura;
    private PokemonType tipo;
    private PokemonType tipodebilidad;
    
    @PodamExclude
    @OneToMany(mappedBy="pokemonEnti",fetch=FetchType.LAZY)
    private List<AtaqueEntity> paradas = new ArrayList<>();

    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    public Double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Double altura) {
        this.altura = altura;
    }

    /**
     * @return the tipo
     */
    public PokemonType getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(PokemonType tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tipodebilidad
     */
    public PokemonType getTipodebilidad() {
        return tipodebilidad;
    }

    /**
     * @param tipodebilidad the tipodebilidad to set
     */
    public void setTipodebilidad(PokemonType tipodebilidad) {
        this.tipodebilidad = tipodebilidad;
    }

    /**
     * @return the paradas
     */
    public List<AtaqueEntity> getParadas() {
        return paradas;
    }

    /**
     * @param paradas the paradas to set
     */
    public void setParadas(List<AtaqueEntity> paradas) {
        this.paradas = paradas;
    }
    
    
    
}
