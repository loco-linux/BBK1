package com.duoc.cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jaime Barraza & Sebastian Olave
 */

public class Cliente {

    
    private static final CuentaCorriente[] clientes = new CuentaCorriente[10];
    private static final Scanner teclado = new Scanner(System.in);
    
    private static int contador = 0;
    
    private static final String[] caracteristicas = {"Rut",
            "Nombre","Apellido paterno","Apellido materno", 
            "Domicilio", "Comuna", "Telefono"
            };
    
    public static void main(String[] args) {
        
        
        int opcion;
        boolean salir=false; // continua=false;
        
        do{
        try{
        System.out.println("\n=======   MENU   =======");
        System.out.println("[1] REGISTRAR CLIENTE");
        System.out.println("[2] VER DATOS DE CLIENTE");
        System.out.println("[3] DEPOSITAR");
        System.out.println("[4] GIRAR");
        System.out.println("[5] CONSULTAR SALDO");
        System.out.println("[6] SALIR");
        System.out.print("---> Ingrese opcion: ");
        
       opcion = teclado.nextInt();
        
       switch(opcion){
           case 1 -> registrarCliente();
           case 2 -> datosCliente();
           case 3 -> depositar();
           case 4 -> girar();
           case 5 -> consultaSaldo();
           case 6 -> {
                System.out.println("\n<<< Hasta pronto >>>\n");    
                salir = true;
                }
           default -> System.out.println("[ERROR] Ingrese una opcion valida!\n"); 

       }
        }catch(InputMismatchException ex) {
            System.out.println("[ERROR] Ingrese un numero entero...\n");
            teclado.next();
            salir = false;
        }
        }while(salir==false);
        
        
    }
    
  
    
    public static void registrarCliente(){
        
        Scanner tecla2 = new Scanner(System.in);
        
        String[] result = {"","","","","","",""};
        int len = caracteristicas.length;
        
        for(int i=0; i<len;i++){
            System.out.print("Ingrese " + caracteristicas[i] + ": ");
            result[i] = tecla2.nextLine();
            if(caracteristicas[i].equals("Rut")){
                if(comprobarRUT(result[0]) == false){
                    System.out.println("[ERROR] rut mal digitado...");
                    break;
                }
                if(existeRUT(result[0]) == true){
                    System.out.println("[ERROR] Rut ya existe!...");
                    break;
                }
            }
        }
        
        try{
        clientes[contador] = new CuentaCorriente(
                result[0], // rut
                result[1], // nombre
                result[2], // apellido paterno
                result[3], // apellido materno
                result[4], // domicilio
                result[5], // comuna
                Integer.parseInt(result[6]), // telefono
                numeroCuenta(result[0]), // cuenta
                0 // saldo
        );
        
        System.out.println("-> Cuenta Corriente: " + clientes[contador].getCuenta());
        contador++;
        }catch(NumberFormatException io){
            System.out.println("[ERROR]");       
        }
        
    
    }
    
    public static void datosCliente(){        
        System.out.print("\nIngrese num Cuenta: ");
        int cuenta, cont = 0; 
        boolean existe = false;
        
        try{
        cuenta = teclado.nextInt();
        
        if(comprobarCuenta(cuenta) != -1){
            cont = comprobarCuenta(cuenta);
            existe = true;
        }else{
            System.out.println("[ERROR] No existe dicha cuenta...");
        }
        
        if(existe) clientes[cont].consultaDatos();
        
        }catch(NullPointerException ex){
            System.out.println("[ERROR] No se han iniciado datos\n");
        }catch(InputMismatchException ex) {
            System.out.println("[ERROR] Ingrese un numero entero...\n");
            teclado.next();
        }
      
    }
    

    public static void depositar(){
        
        System.out.print("\nIngrese num Cuenta: ");
        int cuenta, deposito, cont = 0; 
        boolean existe = false;
        
        try{
        cuenta = teclado.nextInt();
        
        if(comprobarCuenta(cuenta) != -1){
            cont = comprobarCuenta(cuenta);
            existe = true;
        }else{
            System.out.println("[ERROR] No existe dicha cuenta...");
        }
        
        if(existe){
            System.out.print("Ingrese un monto para depositar: ");
            deposito = teclado.nextInt();
            
            clientes[cont].depositar(deposito);       
        }
        }catch(NullPointerException ex){
            System.out.println("[ERROR] No se han iniciado datos\n");
        }catch(InputMismatchException ex) {
            System.out.println("[ERROR] Ingrese un numero entero...\n");
            teclado.next();
        }
        

    }
    
    public static void girar(){
           
        System.out.print("\nIngrese num Cuenta: ");
        int cuenta, giro, cont = 0; 
        boolean existe = false;
        
        try{
        cuenta = teclado.nextInt();
        
        if(comprobarCuenta(cuenta) != -1){
            cont = comprobarCuenta(cuenta);
            existe = true;
        }else{
            System.out.println("[ERROR] No existe dicha cuenta...");
        }
        
        if(existe){
            System.out.print("Ingrese un monto para girar: ");
            giro = teclado.nextInt();
            
            clientes[cont].girar(giro);            
        }
        }catch(NullPointerException ex){
            System.out.println("[ERROR] No se han iniciado datos\n");
        }catch(InputMismatchException ex) {
            System.out.println("[ERROR] Ingrese un numero entero...\n");
            teclado.next();
        }
        
    }
    
    public static void consultaSaldo(){      
        System.out.print("\nIngrese num Cuenta: ");
        int cuenta, cont = 0; 
        boolean existe = false;
        
        try{
        cuenta = teclado.nextInt();
        
        if(comprobarCuenta(cuenta) != -1){
            cont = comprobarCuenta(cuenta);
            existe = true;
        }else{
            System.out.println("[ERROR] No existe dicha cuenta...");
        }
        
        if(existe) clientes[cont].consultar();
        
        }catch(NullPointerException ex){
            System.out.println("[ERROR] No se han iniciado datos\n");
        }catch(InputMismatchException ex) {
            System.out.println("[ERROR] Ingrese un numero entero...\n");
            teclado.next();
        }
        
    }
    
    public static int numeroCuenta(String rut){
        int numeroCuenta;
        int numDigitos = rut.trim().length();
        
        char c_array[] = rut.toCharArray();
        
        char ult4[] = new char[4];
        ult4[3]= c_array[numDigitos-1];
        ult4[2]= c_array[numDigitos-3];
        ult4[1]= c_array[numDigitos-4];
        ult4[0]= c_array[numDigitos-5];
        
        String ultimos = "";
        for(int i=0; i<4; i++){
            ultimos = ultimos + ult4[i];
        }
                   
        int random = (int)Math.floor(Math.random()*10);
        
        String num = "3343" + ultimos + random;
        
        numeroCuenta = Integer.parseInt(num);
        
        return numeroCuenta;
    }
    
    
    public static boolean comprobarRUT(String rut){
        int numDigitos = rut.trim().length();
      
        return !(numDigitos <11 || numDigitos >12);
        
    }
    
    public static boolean existeRUT(String rut){
        boolean existe = false;
        for(int i=0; i<contador; i++){
            if(clientes[i].getRut().equals(rut)){
            existe = true;
            break;
            }
        }
        
        return existe;
    }
    
    public static int comprobarCuenta(int cuenta){
        boolean existe = false;
        int cont=0;
        for(int i=0; i<contador; i++){
            if(clientes[i].getCuenta() == cuenta){
            existe = true;
            break;
            }
            cont++;
        }
        
        if(existe == false){
            return -1;
        }else{
        return cont;
    }
    }
    
}
