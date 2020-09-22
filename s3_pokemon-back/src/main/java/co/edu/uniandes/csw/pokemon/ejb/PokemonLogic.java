/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.ejb;

import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Manuel Porras
 */
@Stateless
public class PokemonLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PokemonLogic.class.getName());
    @Inject
    private PokemonPersistence pokemonPersistence;
    
    /**
     * Se encarga de crear un trayecto en la base de datos.
     *
     * @param pokemonEntity Objeto de CityEntity con los datos nuevos
     * @return Objeto de CityEntity con los datos nuevos y su ID.
     */
    public PokemonEntity createPokemon(PokemonEntity pokemonEntity,ArrayList<AtaqueEntity> ataquesEntities) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del trayecto");
        if (pokemonPersistence.findByNombre(pokemonEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe el pokemon");
        }
        else if(pokemonEntity.getTipo() == pokemonEntity.getTipodebilidad())
        {
            throw new BusinessLogicException("Tiene el mismo tipo de debilidad que el mismo");
        }
        else if(ataquesEntities.size()==0 || ataquesEntities == null)
        {
         throw new BusinessLogicException("No tiene ataques");
        }
        for(AtaqueEntity variable: ataquesEntities)
        {
            if(variable==null)
            {
                throw new BusinessLogicException("Un ataque es null");
            }
            else if(variable.getCantdanio()<10 || variable.getCantdanio()>100)
            {
                throw new BusinessLogicException("El ataque no es válido");
            }
        }
        
        PokemonEntity newTrayectoEntity = pokemonPersistence.create(pokemonEntity);
        newTrayectoEntity.setParadas(ataquesEntities);
        LOGGER.log(Level.INFO, "Termina proceso de creación de trayecto");
        return newTrayectoEntity;
    }
    
}
