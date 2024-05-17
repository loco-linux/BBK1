package com.duoc.cliente;

/**
 *
 * @author Jaime Barraza & Sebastian Olave
 */


// se crea la clase con un nombre
public class CuentaCorriente {
    // 1° se crean los atributos asociados por tipo de dato
    // para encapsular los datos, se utiliza la palabra PRIVATE
    
    private String nombre, apellidoP, apellidoM, rut, domicilio, comuna;
    private int telefono, cuenta, saldo;
    
    
    
    /* 2° se debe crear los constructores. Para poder crear los objetos
    de la clase main().
    Deben ser 2: uno SIN atributos y otro CON atributos 
    */
    
    // sin atributos
    public CuentaCorriente(){
        
    }
    
    // con atributos
    public CuentaCorriente(
            String rut, 
            String nombre, 
            String apellidoP, 
            String apellidoM,
            String domicilio,
            String comuna,
            int telefono,
            int cuenta,
            int saldo
            ){
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cuenta = cuenta;
        this.saldo = saldo;
    }


    // 3° se deben crear los accesadores (GET) y mutadores (SET) para cada atributo
    public String getRut(){
        return rut;
    }
    
    public void setRut(String rut){
        this.rut = rut;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getApellidoP(){
        return apellidoP;
    }
    
    public void setApellidoP(String apellidoP){
        this.apellidoP = apellidoP;
    }
      
    public String getApellidoM(){
        return apellidoM;
    }
    
    public void setApellidoM(String apellidoM){
        this.apellidoM = apellidoM;
    }
    
      
    public String getDomicilio(){
        return domicilio;
    }
    
    public void setDomicilio(String domicilio){
        this.domicilio = domicilio;
    }
    
     
    public String getComuna(){
        return comuna;
    }
    
    public void setComuna(String comuna){
        this.comuna = comuna;
    }
    
    public int getTelefono(){
        return telefono;
    }
    
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
    
    public int getCuenta(){
        return cuenta;
    }
    
    public void setCuenta(int cuenta){
        this.cuenta = cuenta;
    }
    

    public int getSaldo(){
        return saldo;
    }
    
    public void setSaldo(int saldo){
        this.saldo = saldo;
    }

    // 4° se deben crear los metodos necesarios segun el caso
    public void girar(int giro){
        if(saldo - giro < 0){
            System.out.println("[ERROR] El giro no puede ser superior al saldo...");
        }else if(giro<0){
            System.out.println("[ERROR] Ingrese un monto mayor a 0...");
        } else {
            setSaldo(saldo - giro);
            System.out.println("Giro realizado de manera exitosa!");
            System.out.println("--> [" +getCuenta()+"] Saldo actual de $" +getSaldo());
        }
 
    }
    
    public void depositar(int deposito){
        if(deposito < 0){
            System.out.println("[ERROR] Ingrese un monto mayor a cero...");
        } else {
            setSaldo(saldo+deposito);
            System.out.println("Deposito realizado de manera exitosa!");
            System.out.println("--> [" +getCuenta()+"] Saldo actual de $" +getSaldo());
        }
        
    }
    
    public void consultar(){
        System.out.println("--> [" +getCuenta()+"] Saldo disponible: $" +getSaldo());
    }
    
    public void consultaDatos(){
        System.out.println("-> Rut: "               + getRut());
        System.out.println("-> Nombre: "            + getNombre());
        System.out.println("-> Apellido paterno: "  + getApellidoP());
        System.out.println("-> Apellido materno: "  + getApellidoM());
        System.out.println("-> Domicilio: "         + getDomicilio());
        System.out.println("-> Comuna: "            + getComuna());
        System.out.println("-> Telefono: "          + getTelefono());
    }
    
}




