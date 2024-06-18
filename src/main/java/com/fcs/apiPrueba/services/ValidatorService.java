package com.fcs.apiPrueba.services;

import java.util.regex.Pattern;

public class ValidatorService {

    // texto latino: A-Z, a-z, ÁÉÍÓÚÑáéíóúñ, space
    public static boolean esLatino(String texto) {
        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    // frase latina: A-Z, a-z, 0-9, _, space, ÁÉÍÓÚÑáéíóúñ¿?¡!%#
    public static boolean esFrase(String texto) {
        String erTexto = "^[\\w\\sÁÉÍÓÚÑáéíóúñ¿\\x3F¡\\x21\\x25\\x23]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    // fecha: dd/mm/aaaa
    public static boolean esFecha(String texto) {
        String erTexto = "^[0123]\\d\\/[01]\\d\\/[12]\\d{3}$";

        return Pattern.matches(erTexto, texto);
    }

    // hora: 00:00:00 a 23:59:59
    public static boolean esHora(String texto) {
        String erTexto = "^[012]\\d:[0-5]\\d:[0-5]\\d$";

        return Pattern.matches(erTexto, texto);
    }

    // fecha: dd/mm/aaaa hh:mm:ss
    public static boolean esDiaHora(String texto) {
        String erTexto = "^[0123]\\d\\/[01]\\d\\/[12]\\d{3}\\s[012]\\d:[0-5]\\d:[0-5]\\d$";

        return Pattern.matches(erTexto, texto);
    }

    // correo electrónico
    public static boolean esCorreo(String texto) {
        String erTexto = "(^[0-9a-zA-Z]+(?:[._][0-9a-zA-Z]+)*)@([0-9a-zA-Z]+(?:[._-][0-9a-zA-Z]+)*\\.[0-9a-zA-Z]{2,3})$";
        texto = (texto == null) ? "" : texto;

        return Pattern.matches(erTexto, texto);
    }

    // Entre 8 y 10 caracteres, por lo menos un digito y un alfanumérico, y no puede contener caracteres espaciales
    public static boolean esPassword(String texto) {
        String erTexto = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,10})$";

        return Pattern.matches(erTexto, texto);
    }

    // uno o más digitos enteros (\d+), puede haber punto (\.?) y ninguno o varios dígitos decimales (\d*$)
    public static boolean esReal(String texto) {
        String erTexto = "^\\d+\\.?\\d*$";

        return Pattern.matches(erTexto, texto);
    }

    // primer digito [1-9] y luego ninguno o varios dígitos
    public static boolean esEntero(String texto) {
        String erTexto = "^[1-9]\\d*$";

        return Pattern.matches(erTexto, texto);
    }

    // entero: de 1 a N digitos
    public static boolean esEntero(String texto, int ctosdig) {
        String erTexto = "^[1-9]";

        if (ctosdig > 1) {
            erTexto += "\\d{1," + (ctosdig - 1) + "}$";
        }

        return Pattern.matches(erTexto, texto);
    }

    // obliga a ingresar caracteres entre [min, max]
    public static boolean nCaracteres(String texto, int min, int max) {
        String erTexto = ".{" + min + "," + max + "}";

        return Pattern.matches(erTexto, texto);
    }

    //// Validaciones especificas para FERRETERIA
    // texto latino: A-Z, a-z, ÁÉÍÓÚÑáéíóúñ,0-9,",',space,.,/,,
    public static boolean esDescProd(String texto) {

        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ0-9\\x22\\x27\\s\\x2E\\x2F\\x2C\\x2D]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esDocid(String texto) {

        String erTexto = "^[0-9]{8,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esNombre(String texto) {

        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s\\x26\\x2E]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esContacto(String texto) {

        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s\\x28\\x29]{0,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esDireccion(String texto) {

        String erTexto = "^[\\w\\sÁÉÍÓÚÑáéíóúñ¿\\x3F¡\\x21\\x25\\x23\\x2E\\x2D]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esTelefono(String texto) {

        String erTexto = "^[0-9*]{7,}[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]{0,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esRuc(String texto) {

        String erTexto = "^[0-9]{11,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esRazonSocial(String texto) {

        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s\\x26\\x2E]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esAbrNombre(String texto) {

        String erTexto = "^[\\w\\sÁÉÍÓÚÑáéíóúñ¿\\x3F¡\\x21\\x25\\x26\\x23]{1,}$";

        return Pattern.matches(erTexto, texto);
    }

    public static boolean esPlaca(String texto) {
//A-Z, a-z, 0-9
        String erTexto = "^[A-Za-z0-9]{5,6}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esChasis(String texto) {
//A-Z, a-z, 0-9
        String erTexto = "^[A-Za-z0-9]{16,}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esMotor(String texto) {
//A-Z, a-z, 0-9
        String erTexto = "^[A-Za-z0-9]{5,}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esAnho(String texto) {
//0-9
        String erTexto = "^[0-9]{4}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esColor(String texto) {
//A-Z,a-z,zÁÉÍÓÚÑáéíóúñ, ,
        String erTexto = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]{3,}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esCodRpto(String texto) {
//0-9
        String erTexto = "^[0-9A-Za-z]{11}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esCodBarra(String texto) {
//0-9
        String erTexto = "^[0-9A-Za-z]{6,}$";
        return Pattern.matches(erTexto, texto);
    }

    public static boolean esMonedaSDE(String texto) {
//0-9
        String erTexto = "^[S,s,D,d,E,e]{1}$";
        return Pattern.matches(erTexto, texto);
    }

}
