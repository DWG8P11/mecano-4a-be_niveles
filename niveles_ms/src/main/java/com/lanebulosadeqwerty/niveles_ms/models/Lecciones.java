package com.lanebulosadeqwerty.niveles_ms.models;
import java.util.ArrayList;
import javax.persistence.*;
import org.springframework.data.annotation.Id;

@Entity 
@Table(name="leccion") // Para nombre la tabla de la bd
public class Lecciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private Integer n_leccion;
    private String texto;
    private ArrayList<String> teclas = new ArrayList<String>(); 
    private byte imagen;

    private Integer mini1;
    private Integer mini2;
    private Integer mini3;
    private Integer mini4;

    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private Puntajes puntaje;

    @ManyToOne
    @JoinColumn(name = "id")
    private Niveles niveles; 

    //private boolean ignorar_mayus;
    //private boolean ignorar_tildes;
    //private Boolean ignorar_dieres;


    public Lecciones(Integer id, String titulo,Integer n_leccion, String texto, ArrayList<String> teclas, byte imagen ,
                     Integer mini1, Integer mini2,Integer mini3, Integer mini4) {
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
        }
        public Lecciones() {}


    public Integer getId() {
        return id;
        }
        
    public void setNombre(Integer id) {
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


}
