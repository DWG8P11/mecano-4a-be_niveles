package com.lanebulosadeqwerty.niveles_ms.models;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.util.Date;

import javax.persistence.*;

@Entity // Le indicamos a la clase que es una entidad, para administrarla y realizar las relaciones 
@Table(name="puntajes") // Para nombrar la tabla
public class Puntajes{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // autoincremental
    private Integer id;
    private String usuario;
    private double precision;
    private Integer cpm_t;
    private Integer cpm_e;
    private Time tiempo;
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "id")
    private Lecciones lecciones;

    public Puntajes(Integer id, String usuario,double precision,Integer cpm_t, Integer cpm_e, Time tiempo, Date fecha) {
        this.id = id;
        this.precision = precision;
        this.usuario = usuario;
        this.cpm_t = cpm_t;
        this.cpm_e = cpm_e;
        this.tiempo = tiempo;
        this.fecha = fecha;
        }
    public Puntajes() {}

    public Integer getId() {
        return id;
        }
        
    public void setNombre(Integer id) {
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



}