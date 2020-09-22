/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Manuel Porras
 */
@Entity
public class AtaqueEntity  extends BaseEntity implements Serializable{
    
    private String nombre;
    private Integer cantdanio;
    
    @PodamExclude
    @ManyToOne
    private PokemonEntity pokemonEnti;

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
     * @return the cantdanio
     */
    public Integer getCantdanio() {
        return cantdanio;
    }

    /**
     * @param cantdanio the cantdanio to set
     */
    public void setCantdanio(Integer cantdanio) {
        this.cantdanio = cantdanio;
    }

    /**
     * @return the pokemonEnti
     */
    public PokemonEntity getPokemonEnti() {
        return pokemonEnti;
    }

    /**
     * @param pokemonEnti the pokemonEnti to set
     */
    public void setPokemonEnti(PokemonEntity pokemonEnti) {
        this.pokemonEnti = pokemonEnti;
    }
    
    
    
}
