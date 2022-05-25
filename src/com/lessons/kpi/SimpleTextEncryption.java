package com.lessons.kpi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class SimpleTextEncryption {

    public static void main(String args[]) throws Exception {
        new SimpleTextEncryption().encryptText("Hello Joel Niwogaba");
    }

    private void encryptText(String text) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        //Creating KeyPair generator object
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");

        //Initialize the key pair generator
        keyPairGenerator.initialize(2048);

        //Generate the pair of keys
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //Get the private key from key pair
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        //Create a Signature Object
        Signature signature = Signature.getInstance("SHA256withDSA");

        //Initialize the signature
        signature.initSign(privateKey);
        byte[] bytes = text.getBytes();

        //Add data to the signature
        signature.update(bytes);

        //Calculate the signature
        byte[] sign = signature.sign();

        System.out.println("Digital signature for given text: "+new String(sign, "UTF8"));

        //verify key
        //Initializing the verify signature
        signature.initVerify(publicKey);
        signature.update(bytes);
        //Verify the signature
        boolean bool = signature.verify(sign);
        System.out.println("Verify Digital signature for given text: "+bool);


    }

}
