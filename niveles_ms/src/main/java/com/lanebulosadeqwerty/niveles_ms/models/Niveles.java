package com.lanebulosadeqwerty.niveles_ms.models;
import org.springframework.data.annotation.Id;

import java.util.Collection;

import javax.persistence.*;

@Entity 
@Table(name="nivel") // Nombrar tabla de la bd
public class Niveles{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String descripcion;
    private byte imagen;

    @OneToMany(mappedBy = "id")
    private Collection<Lecciones> lecciones; 

    public Niveles(Integer id,String nombre, String descripcion, byte imagen) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        }
        public Niveles() {}

    public Integer getId() {
        return id;
        }
    
    public void setNombre(Integer id) {
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
    
    public byte getImagen() {
        return imagen;
        }
                    
    public void SetImagen(byte imagen) {
        this.imagen = imagen;
        }  

}


