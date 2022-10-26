package com.example.junk.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Collection;

@Entity
@Data
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(unique = true)
    private String username;
    private String password;

    //Классный метод для хеширования пароля, который я не могу преобразоать обратно(
    public static String encryptPassword( String password ) {

        String encrypted = "";
        try {
            MessageDigest digest = MessageDigest.getInstance( "MD5" );
            byte[] passwordBytes = password.getBytes( );

            digest.reset( );
            digest.update( passwordBytes );
            byte[] message = digest.digest( );

            StringBuffer hexString = new StringBuffer();
            for ( int i=0; i < message.length; i++)
            {
                hexString.append( Integer.toHexString(
                        0xFF & message[ i ] ) );
            }
            encrypted = hexString.toString();
        }
        catch( Exception e ) { }
        return encrypted;
    }

    public User(){}

}
