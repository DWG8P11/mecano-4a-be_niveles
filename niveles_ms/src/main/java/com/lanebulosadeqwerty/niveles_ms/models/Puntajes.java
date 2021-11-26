package com.lanebulosadeqwerty.niveles_ms.models;
import org.springframework.data.annotation.Id;

// import java.sql.Double;
import java.util.Date;

import javax.persistence.*;

@Entity // Le indicamos a la clase que es una entidad, para administrarla y realizar las relaciones 
@Table(name="puntajes") // Nombrar tabla de la bd
public class Puntajes{
    @Id
    private String id;
    private String usuario; // Nombre de usuario
    private double precision;
    private Integer cpm_e;
    private Double segundos;
    private Date fecha;

    // // @OneToOne
    // @ManyToOne
    // @JoinColumn(name = "id")
    // private String leccion_id;
    private String leccion_id;

    public Puntajes(String id, String usuario,double precision, Integer cpm_e, Double segundos, Date fecha, String leccion_id) {
        this.id = id;
        this.precision = precision;
        this.usuario = usuario;
        this.cpm_e = cpm_e;
        this.segundos = segundos;
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
        
    public Integer getCpm_e() {
        return cpm_e;
        }
            
    public void setCpm_e(Integer cpm_e) {
        this.cpm_e = cpm_e;
        }        
        
    public Double getSegundos() {
        return segundos;
        }
                
    public void setSegundos(Double segundos) {
        this.segundos = segundos;
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