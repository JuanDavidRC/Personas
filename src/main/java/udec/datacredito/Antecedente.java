
package udec.datacredito;

import java.io.Serializable;
/**
 *  @author JUAN DAVID REYES
 * @author ALEJANDRO TORRES
 */

public class Antecedente implements Serializable{
    /**
     *atributos de la clase 
     */
    private int codigo;
    
    private String fecha;
    
    private String descripcion;
    
    private boolean estado;
    /**
     * constructor de la clase
     * @param codigo
     * @param fecha
     * @param descripcion
     * @param estado 
     */
    public Antecedente(int codigo, String fecha, String descripcion, boolean estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
   /**
    * metodos get y set
    * @return 
    */

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
   
}
