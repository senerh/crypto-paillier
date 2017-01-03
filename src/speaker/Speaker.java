package speaker;

import rsa.InterfaceRSA;

import java.math.BigInteger;

public class Speaker {

    private String name;
    private InterfaceRSA rsa;
    private BigInteger m;

    public Speaker(String name, InterfaceRSA rsa) {
        this.name = name;
        this.rsa = rsa;
    }

    /**
     * Recevoir un message non chiffré
     * @param m message reçu (non chiffré)
     */
    public void receive(BigInteger m) {
        System.out.println(name + " received : " + m);
        this.m = m;
    }

    /**
     * Recevoir un message chiffré
     * @param m message reçu (chiffré)
     * @param e clé publique
     * @param n module de chiffrement
     */
    private void receive(BigInteger m, BigInteger e, BigInteger n) {
        receive(encrypt(m, e, n));
    }

    /**
     * Envoyer un message non chiffré
     * @param receiver destinataire
     * @param m message non chiffré
     */
    public void send(Speaker receiver, BigInteger m) {
        System.out.println(name + " -> " + receiver.name + " : " + m);
        receiver.receive(m);
    }

    /**
     * Envoyer un message chiffré
     * @param receiver destinataire
     * @param m message non chiffré
     */
    public void sendEncrypted(Speaker receiver, BigInteger m) {
        BigInteger encryptedM = encrypt(m, rsa.getD(), rsa.getN());
        System.out.println(name + " -> " + receiver.name + " : " + encryptedM);
        receiver.receive(encryptedM, rsa.getE(), rsa.getN());
    }

    /**
     * Chiffre (ou déchiffre) un message
     * @param m message
     * @param d clé de chiffrement (ou déchiffrement)
     * @param n module de chiffrement
     * @return message déchiffré (ou chiffré)
     */
    private BigInteger encrypt(BigInteger m, BigInteger d, BigInteger n) {
        return m.modPow(d, n);
    }
}
