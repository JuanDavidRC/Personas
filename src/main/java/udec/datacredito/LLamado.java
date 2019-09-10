
package udec.datacredito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *  @author JUAN DAVID REYES
 * @author ALEJANDRO TORRES
 */
public class LLamado implements Serializable{
    /**
     * entrada de los datos
     */
    Scanner entrada = new Scanner(System.in);
    /**
     * Constructor de la clae
     */
    public LLamado() {
        menu();

    }
     /**
     * menu de opciones de el programa
     */
    public void menu(){
        System.out.println("BIENVENIDO");
        System.out.println("A continuacion seleccione la opcion deseada:");
        try {
            System.out.println("1. Agregar Persona");
            System.out.println("2. Añadir Antecedente a persona");
            System.out.println("3. Ver Persona");
            System.out.println("4. Modificar Persona");
            System.out.println("5. Eliminar Antecedente");
            System.out.println("6.Modificar estado de Antecedente");
            System.out.println("7. Salir");
            byte opcion = entrada.nextByte();
            seleccion(opcion);
        } catch (InputMismatchException e) {
            System.out.println("Por favor digite un numero valido");
            entrada=new Scanner(System.in);
            menu();
        }
    }
     /**
     * menu del programa
     * @param opcion parametro que recibe la opcion deigitada por el usuario sobre la accion que desea realizar
     */
    private void seleccion(byte opcion){
        switch(opcion){
            case 1:{
                datosPersona();
                break;
            }case 2:{
                int id = capturaCedula();
                Persona persona = tomarPersona(id);
                datosAntecedente(persona);
                break;
            }case 3:{
                int id = capturaCedula();
                ArrayList<Persona> persona =buscaPersona(id);
                impresion(persona, id);
                break;
            }case 4:{
                int id = capturaCedula();
                ArrayList<Persona> persona =buscaPersona(id);
                modificar(persona, id);
                menu();
                break;
            }case 5:{
                eliminarAntecedente();
                menu();
                break;
            }case 6:{
                cambiarEstado();
                menu();
                break;
            }case 7:{
                System.exit(0);
            }default:{
                System.out.println("Seleccion invalida intente de nuevo");
                menu();
                break;
            }
        }
    }
    
    
    /**
     * metodo que recibe los datos de persona y los guarda en el fichero 
     * @param persona 
     */
    public void escritura(Persona persona){
        try  {
            File archivo=new File("Archivo\\documento.txt");
            ArrayList<Persona> lista = new ArrayList();
            if(archivo.exists()){
                ObjectInputStream  objectInput = new ObjectInputStream(new FileInputStream(archivo));
                lista = (ArrayList<Persona>) objectInput.readObject();
            }
            eliminarRepetidos(lista, persona);
            lista.add(persona);
            ObjectOutputStream escritura = new ObjectOutputStream( new FileOutputStream(archivo));
            
            escritura.writeObject(lista);
 
            escritura.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LLamado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * metodo vacio que lee  los datos 
     * @return 
     */
    private ArrayList lectura(){
         try(FileInputStream fis=new FileInputStream("Archivo\\documento.txt")){
             ObjectInputStream lectura = new ObjectInputStream(fis);
             ArrayList<Persona> persona = (ArrayList<Persona>) lectura.readObject();
             lectura.close();
             return persona;
        }catch(IOException e){
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LLamado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * metodo vacio que captura los datos por medio de llamado de objeto de la clase y los 
     * guarda en una lista
     */
    private void datosPersona(){
        try {
            System.out.println("Ingrese cedula de la persona: ");
            int identificacion = entrada.nextInt();
            System.out.println("Ingrese el nombre de la persona: ");
            String nombre  = entrada.next();
            
            System.out.println("Ingrese el apellido de la persona: ");
            String apellido = entrada.next();
            
            System.out.println("Ingrese el genero de la persona: ");
            String genero = entrada.next();
            
            System.out.println("Ingrese la edad de la persona: ");
            String edad = entrada.next();
            
            Persona persona = new Persona(identificacion, nombre, apellido,genero, edad);
            escritura(persona);
            menu();
        } catch (InputMismatchException e) {
            System.out.println("Datos incorrectos intente de nuevo");
            entrada = new Scanner(System.in);
            datosPersona();
        }     
    }
    /**
     * validacion de una persona repetida por medio de la cedula 
     * no la guarda en caso de que este repetida
     * @param lista
     * @param persona
     * @return 
     */
    private Persona eliminarRepetidos(ArrayList<Persona> lista,Persona persona){
        ArrayList<Persona> prueba = new ArrayList<>(lista);
        try {
            for (Persona lista1 : prueba) {
            if(lista1.getCedula() == persona.getCedula()){
                lista.remove(lista1);
            }
        }
        } catch (Exception e) {
        }
        
        return persona;
    }
    /**
     * captura los datos del antecedente 
     * @param persona 
     */
    private void datosAntecedente(Persona persona){
        try {
            System.out.println("\nIngrese codigo del Antecedente 1 positivo 2 negativo: ");
            int codigo = entrada.nextInt();
            System.out.println("Ingrese la fecha dd/mm/aa: ");
            String fecha = entrada.next();
            System.out.println("Ingrese la descripcion de el antecedente: ");
            String descr = entrada.next();
            Antecedente ant = new Antecedente(codigo, fecha, descr, true);
            persona.getAntecedente().add(ant);
            escritura(persona);
            menu();
        } catch (InputMismatchException e) {
            System.out.println("Datos incorrectos intente de nuevo");
            entrada = new Scanner(System.in);
            datosAntecedente(persona);
        }
    }
   
   
    /**
     * captura la cedula y la  compara para buscar 
     * datos 
     * @return 
     */
    private int capturaCedula(){
        System.out.println("Ingrese cedula de la persona: ");
        int identificacion = entrada.nextInt();
        return identificacion;
    }
    /**
     * metodo que realiza la busqueda de una persona por la identificacion
     */
    private ArrayList buscaPersona(int cedula){
        ArrayList<Persona> lista = lectura();
        ArrayList<Persona> datos = new ArrayList();
        for (Persona persona : lista) {
            if(persona.getCedula() == cedula){
                datos.add(persona);
            }
        }return datos;
    }
    /*
     *busca la cedula y  ayuda a guardar los datos de antecedente en lista persona
     *
    */
    private Persona tomarPersona(int identificacion){
        ArrayList<Persona> lista = lectura();
        Persona datos=null;
        for (Persona persona : lista) {
            if(persona.getCedula() == identificacion){
               datos = persona;
            }
        }return datos;
    }
    /**
     * metodo que realiza la impresion de la persona con sus records respectivos
     * @param persona parametro que recibe el objeto con los datos especificos de la person a mostrar
     */
    public void modificar(ArrayList<Persona> persona, int cedula){
        Object actual = null;
        boolean bandera=true;
        ArrayList<Persona> lista = null;
        int contador =0;
      while(contador < persona.size()) {
            actual = persona;
            lista = (ArrayList<Persona>) actual;
            for (int i = 0; i < lista.size(); ++i) {
                if(lista.get(i).getCedula() == cedula){
                    if(bandera){
                         System.out.println("\nCedula: " + lista.get(i).getCedula());
                         System.out.println("Ingrese el nombre de la persona: ");
                         String nombre  = entrada.next();
                         lista.get(i).setNombre(nombre);
                         System.out.println("Ingrese el apellido de la persona: ");
                         String apellido = entrada.next();
                         lista.get(i).setApellido(apellido);
                         System.out.println("Ingrese el genero de la persona: ");
                         String genero = entrada.next();
                         lista.get(i).setGenero(genero);
                         System.out.println("Ingrese la edad de la persona: ");
                         String edad = entrada.next();
                         lista.get(i).setEdad(edad);
                        bandera = false;
                       
                    
                    }
                    
                    contador++;
                    
                }
            }
        }
        menu();
    }
    /**
     * metodo que comprueba que tipo de antecedente es
     * @param estado
     * @return 
     */
    private String estado(boolean estado){
        if(estado){
            return "Positivo";
        }else{
            return "Negativo";
        }
    }
    
    private void cambiarEstado(){
        try {
             System.out.println("Digite la Cedula de la persona: ");
            int cedula = entrada.nextInt();
            Persona persona = tomarPersona(cedula);
            System.out.println("Digite el codigo del Antecedente a modificar: ");
            int codigo = entrada.nextInt();
            int contador =0;
            for (Antecedente ante : persona.antecedente) {
                if(persona.antecedente.get(contador).getCodigo() ==codigo){
                    persona.antecedente.get(contador).setEstado(false);
                }
            }
            escritura(persona);
        } catch (InputMismatchException e) {
            System.out.println("Dato incorrecto por favor intente nuevamente");
            entrada = new Scanner(System.in);
            cambiarEstado();
        }
    }
    public void impresion(ArrayList<Persona> persona, int cedula){      
        Object actual = null;
        boolean bandera=true;
        ArrayList<Persona> lista = null;
        int contador =0;
      while(contador < persona.size()) {
            actual = persona;
            lista = (ArrayList<Persona>) actual;
            for (int i = 0; i < lista.size(); ++i) {
                if(lista.get(i).getCedula() == cedula){
                    if(bandera){
                        System.out.println("\nCedula: " + lista.get(i).getCedula());
                        System.out.println("Nombre: "+lista.get(i).getNombre());
                        System.out.println("Apellido: "+lista.get(i).getApellido());
                        System.out.println("Genero: "+lista.get(i).getGenero());
                        System.out.println("Edad: "+lista.get(i).getEdad());
                        bandera = false;
                    }
                    for (Persona lista1 : lista) {
                        if(lista1.antecedente.isEmpty()){
                            System.out.println("La persona no tiene Antecedentes");
                            contador++;
                        }else{
                        System.out.println("\nAntecedente "+(contador+1));
                        System.out.println("codigo : "+lista1.antecedente.get(contador).getCodigo());
                        System.out.println("Fecha dd/mm/aa: "+lista1.antecedente.get(contador).getFecha()); 
                        System.out.println("Descripcion: "+lista1.antecedente.get(contador).getDescripcion());
                        System.out.println("Estado: "+estado(lista1.antecedente.get(contador).isEstado()));
                        contador++;
                        }
                    }
                }
            }
        }
        menu();
    }
    /**
     * elimina antecedente negativo
     */
    private void eliminarAntecedente(){
        Persona persona=null;
        try {
            System.out.println("Digite la Cedula de la persona: ");
            int cedula = entrada.nextInt();
            persona = tomarPersona(cedula);
            System.out.println("Digite el codigo del Antecedente a eliminar: ");
            int codigo = entrada.nextInt();
            int contador =0;
            ArrayList<Antecedente> ante = new ArrayList<Antecedente>(persona.antecedente) ;
            for (Antecedente antece : ante) {
                if(persona.antecedente.get(contador).getCodigo() == 2){
                    persona.antecedente.remove(persona.antecedente.get(contador));
                }else{
                    System.out.println("El estado es positivo, no se puede eliminar el record");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor ingrese datos validos");
            entrada = new Scanner(System.in);
            eliminarAntecedente();
        }
        escritura(persona);
    }
}
