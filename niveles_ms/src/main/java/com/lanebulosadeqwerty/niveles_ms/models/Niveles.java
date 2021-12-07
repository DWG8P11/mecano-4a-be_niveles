package com.lanebulosadeqwerty.niveles_ms.models;
import org.springframework.data.annotation.Id;

// import java.util.Collection; // List?

import javax.persistence.*;

@Entity 
@Table(name="nivel") // Nombrar tabla de la bd
public class Niveles{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;

    // @OneToMany(mappedBy = "id")
    // private Collection<Lecciones> lecciones; 

    public Niveles(Integer id, String nombre, String descripcion, String imagen) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
        

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
         this.id = id;
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
    
    public String getImagen() {
        return imagen;
        }
                    
    public void SetImagen(String imagen) {
        this.imagen = imagen;
        }  

}


