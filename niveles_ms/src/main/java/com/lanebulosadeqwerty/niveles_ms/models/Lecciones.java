package com.lanebulosadeqwerty.niveles_ms.models;
import java.util.ArrayList;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

@Entity 
@Table(name="leccion") // Nombrar tabla de la bd
public class Lecciones {
    @Id
    private String id;
    @NotBlank
    private String titulo;
    @NotNull
    private Integer n_leccion;
    @NotBlank
    private String texto;
    private ArrayList<String> teclas = new ArrayList<String>(); // -> //TODO Character
    private byte imagen;

    @NotNull
    @Min(0)
    private Integer mini1;
    @NotNull
    private Integer mini2;
    @NotNull
    private Integer mini3;
    @NotNull
    private Integer mini4;

    // @ManyToOne
    // @JoinColumn(name = "id")
    @NotNull
    private Integer nivel;  //TODO Falta un Class level validator para poder validar que la combinacion de nivel y nLeccion es unica, y que los puntajes minimos no son decrecientes

    // @OneToMany(mappedBy = "id")//, cascade = CascadeType.ALL)
    // private List<Puntajes> puntaje;

    // @Value("${this.ignorarMayus: true}")
    private Boolean ignorarMayus;
    // @Value("${this.ignorarTildes: true}")
    private Boolean ignorarTildes;
    // @Value("${this.ignorarDieres: true}")
    private Boolean ignorarDieres;

    public Lecciones(String titulo,Integer n_leccion, String texto, ArrayList<String> teclas, byte imagen ,
                     Integer mini1, Integer mini2,Integer mini3, Integer mini4, Integer nivel, 
                     Boolean ignorarMayus, Boolean ignorarTildes, Boolean ignorarDieres) {
                              
        this.titulo = titulo;
        this.n_leccion = n_leccion;
        this.texto = texto;
        this.teclas = teclas;
        this.imagen=imagen;
        this.mini1 = mini1;
        this.mini2 = mini2;
        this.mini3 = mini3;
        this.mini4 = mini4;
        this.nivel = nivel;

        this.ignorarMayus = ignorarMayus;
        this.ignorarTildes = ignorarTildes;
        this.ignorarDieres = ignorarDieres;
    }
        
    // public Lecciones() {}


    public String getId() {
        return id;
        }
        
    public void setId(String id) {
        this.id = id;
        }

    public String getTitulo() {
        return titulo;
        }
    
    public void setTitulo(String titulo) {
         this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
        }

    public void setTexto(String texto) {
        this.texto = texto;
        }

    public Integer getN_leccion() {
        return n_leccion;
        }
            
    public void setN_leccion(Integer n_leccion) {
        this.n_leccion = n_leccion;
    }

    public ArrayList<String> getTeclas() {
        return teclas;
        }
            
    public void setTeclas(ArrayList<String> teclas) {
        this.teclas = teclas;
    }

    public Byte getImagen() {
        return imagen;
        }
            
    public void setImagen(Byte imagen) {
        this.imagen = imagen;
    }

    public Integer getMini1() {
        return mini1;
        }
            
    public void setMini1(Integer mini1) {
        this.mini1 = mini1;
    }

    public Integer getMini2() {
        return mini2;
        }
            
    public void setMini2(Integer mini2) {
        this.mini2 = mini2;
    }

    public Integer getMini3() {
        return mini3;
        }
            
    public void setMini3(Integer mini3) {
        this.mini3 = mini3;
    }

    public Integer getMini4() {
        return mini4;
        }
            
    public void setMini4(Integer mini4) {
        this.mini4 = mini4;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Boolean getIgnorarMayus() {
        return this.ignorarMayus;
    }

    public void setIgnorarMayus(Boolean ignorarMayus) {
        this.ignorarMayus = ignorarMayus;
    }

    public Boolean getIgnorarTildes() {
        return this.ignorarTildes;
    }

    public void setIgnorarTildes(Boolean ignorarTildes) {
        this.ignorarTildes = ignorarTildes;
    }

    public Boolean getIgnorarDieres() {
        return this.ignorarDieres;
    }

    public void setIgnorarDieres(Boolean ignorarDieres) {
        this.ignorarDieres = ignorarDieres;
    }
    

}
