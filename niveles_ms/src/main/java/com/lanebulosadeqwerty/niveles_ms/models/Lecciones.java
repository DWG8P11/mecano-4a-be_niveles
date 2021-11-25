package com.lanebulosadeqwerty.niveles_ms.models;
import java.util.ArrayList;
import javax.persistence.*;
import org.springframework.data.annotation.Id;
import java.util.List;

@Entity 
@Table(name="leccion") // Nombrar tabla de la bd
public class Lecciones {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String titulo;
    private Integer n_leccion;
    private String texto;
    private ArrayList<String> teclas = new ArrayList<String>(); 
    private byte imagen;

    private Integer mini1;
    private Integer mini2;
    private Integer mini3;
    private Integer mini4;

    @ManyToOne
    @JoinColumn(name = "id")
    private Integer nivel; 

    // @OneToMany(mappedBy = "id")//, cascade = CascadeType.ALL)
    // private List<Puntajes> puntaje;

    private Boolean ignorarMayus;
    private Boolean ignorarTildes;
    private Boolean ignorarDieres;

    public Lecciones(String id, String titulo,Integer n_leccion, String texto, ArrayList<String> teclas, byte imagen ,
                     Integer mini1, Integer mini2,Integer mini3, Integer mini4, Integer nivel, 
                     Boolean ignorarMayus, Boolean ignorarTildes, Boolean ignorarDieres) {
        this.id=id;                        
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
