package com.lanebulosadeqwerty.niveles_ms.models;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.util.Date;

import javax.persistence.*;

@Entity // Le indicamos a la clase que es una entidad, para administrarla y realizar las relaciones 
@Table(name="puntajes") // Nombrar tabla de la bd
public class Puntajes{
    @Id
    private String id;
    private String usuario; // Nombre de usuario
    private double precision;
    private Integer cpm_t;
    private Integer cpm_e;
    private Time tiempo;
    private Date fecha;

    // // @OneToOne
    // @ManyToOne
    // @JoinColumn(name = "id")
    // private String leccion_id;
    private String leccion_id;

    public Puntajes(String id, String usuario,double precision,Integer cpm_t, Integer cpm_e, Time tiempo, Date fecha, String leccion_id) {
        this.id = id;
        this.precision = precision;
        this.usuario = usuario;
        this.cpm_t = cpm_t;
        this.cpm_e = cpm_e;
        this.tiempo = tiempo;
        this.fecha = fecha;
        this.leccion_id = leccion_id;
        }
    public Puntajes() {}

    public String getId() {
        return id;
        }
        
    public void setNombre(String id) {
        this.id = id;
        }

    public double getPrecision() {
        return precision;
        }
            
    public void setPrecision(double precision) {
        this.precision = precision;
    }  

    public String getUsuario() {
        return usuario;
        }
                
    public void setUsuario(String usuario) {
        this.usuario = usuario;
        }  

    public Integer getCpm_t() {
        return cpm_t;
        }
        
    public void setCpm_t(Integer cpm_t) {
        this.cpm_t = cpm_t;
        }      
        
    public Integer getCpm_e() {
        return cpm_e;
        }
            
    public void setCpm_e(Integer cpm_e) {
        this.cpm_e = cpm_e;
        }        
        
    public Time getTiempo() {
        return tiempo;
        }
                
    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
        }          

    public Date getFecha() {
        return fecha;
        }
                    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
        }  

    public String getleccion_id() {
        return this.leccion_id;
    }

    public void setleccion_id(String leccion_id) {
        this.leccion_id = leccion_id;
    }

}