
package udec.datacredito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JUAN DAVID REYES
 * @author ALEJANDRO TORRES
 */
public class Persona implements Serializable{
    /**
     * atributos de la clase
     */
    private int cedula;
    
    private String nombre;
    
    private String apellido;
    
    private String edad;
    
    private String genero;
    
    List<Antecedente> antecedente = new ArrayList();
    /**
     * constructor de la clse
     * @param cedula
     * @param nombre
     * @param apellido
     * @param genero
     * @param edad 
     */
    public Persona(int cedula, String nombre, String apellido, String genero, String edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
    }
    /**
     * metodos de la clase get y set
     * @return 
     */
   
   

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
    public List<Antecedente> getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(List<Antecedente> antecedente) {
        this.antecedente = antecedente;
    }
    
    
    
}
