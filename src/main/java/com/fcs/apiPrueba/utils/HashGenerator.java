package com.fcs.apiPrueba.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashGenerator {
    public static String generateHash(String password) {
        String salt = BCrypt.gensalt();
        // Generar el hash con salt BCrypt
        return BCrypt.hashpw(password, salt);

        /// Para comparar el password (true or false) con el hash:
        /// BCrypt.checkpw("clave",persona.getPassword());
    }
}
