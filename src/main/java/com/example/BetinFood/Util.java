package com.example.BetinFood;
import java.util.Date;

import com.example.BetinFood.Cliente.Cliente;

import jakarta.servlet.http.HttpSession;

public class Util {
    public static void setClienteSession(HttpSession session,Cliente cli){
        Cliente cliente = new Cliente();
        cliente.setNombres(cli.getNombres());
        cliente.setApellidos(cli.getApellidos());
        cliente.setCorreo(cli.getCorreo());
        session.setAttribute("cliente", cliente);
    }
    public static Cliente getClienteSession(HttpSession session){
        Cliente cliente = (Cliente)session.getAttribute("cliente");
        if(cliente==null){
            cliente = new Cliente();
            cliente.setNombres("Invitado");
            cliente.setApellidos("Invitado");
            cliente.setCorreo("Invitado");

        }

        return cliente;
    }
    //metodo para validar la cantidad de intentos de inicio de sesion con el fin de bloquear la cuenta hasta que se cumpla el tiempo de baneo
    public static void setIntentosSession(HttpSession session,int intentos){
        Date fecha = new Date();
        session.setAttribute("intentos", intentos);
        if(intentos==3){
            session.setAttribute("fechaBloqueo", fecha);
        }
    }   
    public static int getIntentosSession(HttpSession session){
        if(session.getAttribute("intentos")==null){
            session.setAttribute("intentos", 0);
        }
        int intentos = (int)session.getAttribute("intentos");
        if(intentos==4){
            Date fecha = (Date)session.getAttribute("fechaBloqueo");
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime()-fecha.getTime();
            long segundos = diferencia/1000;
            System.out.println("segundos: "+segundos);
            if(segundos>=60){
                intentos=0;
                session.setAttribute("intentos", intentos);
            }
        }
        return intentos;
    }
//validar una cadena de texto que sea un correo electronico con expresiones regulares
public static boolean validarCorreo(String correo){
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return correo.matches(regex);
}
//validar una cadena de texto que sea una contraseÃ±a con expresiones regulares que contenga al menos una letra mayuscula, una minuscula, un numero y un caracter especial
public static boolean validarContra(String contra){
    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    return contra.matches(regex);
}
}
