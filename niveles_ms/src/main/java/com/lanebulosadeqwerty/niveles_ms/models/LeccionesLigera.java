package com.lanebulosadeqwerty.niveles_ms.models;

import java.util.ArrayList;

public interface LeccionesLigera {
    String getId();
    Integer getNivel();
    Integer getN_leccion(); 
    String getTitulo();
    String getTexto(); // Tal vez omitir?
    ArrayList<String> getTeclas(); // Tal vez omitir?
    Integer getMini1(); // Tal vez omitir?
    Integer getMini2(); // Tal vez omitir?
    Integer getMini3(); // Tal vez omitir?
    Integer getMini4(); // Tal vez omitir?
    Boolean getIgnorarMayus(); // Tal vez omitir?
    Boolean getIgnorarTildes(); // Tal vez omitir?
    Boolean getIgnorarDieres(); // Tal vez omitir?
}
