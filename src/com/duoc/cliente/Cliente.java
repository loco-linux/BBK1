package com.duoc.cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jaime Barraza & Sebastian Olave
 */

public class Cliente {

    // Atributos encapsulados
    // Arreglo de constructor del objeto clientes tipo CuentaCorriente
    // con capacidad para almacenar 10 cuentas (esto fue arbitrario, podría ser otro valor)
    private static final CuentaCorriente[] clientes = new CuentaCorriente[10];
    private static final Scanner teclado = new Scanner(System.in);
    
    private static int contador = 0;
    
    // Atributos a solicitar al usuario
    private static final String[] caracteristicas = {"Rut",
            "Nombre","Apellido paterno","Apellido materno", 
            "Domicilio", "Comuna", "Telefono"
            };
    
    public static void main(String[] args) {
        
        
        int opcion;
        boolean salir=false; 
        
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
            if(contador == 10){
                System.out.println("[ERROR] Ya se ocuparon las 10 cuentas");
                break;
            }
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
        
        if(contador<10){
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
                0 // saldo (ya seteado a $0 pesos)
        );
        
        System.out.println("-> Cuenta Corriente: " + clientes[contador].getCuenta());
        contador++;
        
        }catch(NumberFormatException io){
            System.out.println("[ERROR]");       
        }
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
        ult4[3]= c_array[numDigitos-3]; // ultimos 4 digitos antes del gion
        ult4[2]= c_array[numDigitos-4]; // 
        ult4[1]= c_array[numDigitos-5];
        ult4[0]= c_array[numDigitos-7];
        
        String ultimos = "";
        for(int i=0; i<4; i++){
            ultimos = ultimos + ult4[i];
        }
        // linea que devuelve un numero entero al azar del 0 al 9           
        int random = (int)Math.floor(Math.random()*10);
        // String que agrupa el prefijo del banco + 4 ultimos digitos del rut + random
        // Generando los 9 digitos requeridos para la cuenta corriente
        String num = "3343" + ultimos + random;
        // linea que convierte el tipo String a int
        numeroCuenta = Integer.parseInt(num);
        
        return numeroCuenta;
    }
    
    // Metodo que comprueba la longitud de caracteres del rut ingresado
    public static boolean comprobarRUT(String rut){
        int numDigitos = rut.trim().length();
      
        return !(numDigitos <11 || numDigitos >12);
        
    }
    
    // Metodo que comprueba si existe el rut ingresado. Si ya esta ingresado, retorna true
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
    
    // Metodo que comprueba la existencia de una cuenta en particular
    // Si no existe, retorna -1. 
    // De lo contrario, retorna el indice del arreglo donde encontro el num de cuenta
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
