/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.test.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Manuel Porras
 */
@RunWith(Arquillian.class)
public class PokemonPersistenceTest {
    
    @Inject
    private PokemonPersistence tp;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PokemonEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive retornarJavaArchive() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);
            em.persist(entity);
            data.add(entity);
            System.out.println(data.size());
        }
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity trayecto = factory.manufacturePojo(PokemonEntity.class);
        PokemonEntity result = tp.create(trayecto);
        Assert.assertNotNull(result);
    }

    @Test
    public void createPokemonTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity trayecto = factory.manufacturePojo(PokemonEntity.class);
        PokemonEntity result = tp.create(trayecto);

        Assert.assertNotNull(result);

        PokemonEntity entity = em.find(PokemonEntity.class, result.getId());

        Assert.assertEquals(trayecto.getNombre(), entity.getNombre());
        

    }
    
}
