package speaker;

import paillier.Paillier;

import java.math.BigInteger;

public class Speaker {

    private String name;
    private Paillier paillier;
    private BigInteger m;
    private BigInteger c;

    public Speaker(String name, Paillier paillier) {
        this.name = name;
        this.paillier = paillier;
    }

    public BigInteger getM() {
        return m;
    }

    public BigInteger getC() {
        return c;
    }

    public void receive(BigInteger m) {
        this.m = m;
        System.out.println(name + " received : " + m);
    }

    public void receivePaillier(BigInteger c, Paillier paillier) {
        this.c = c;
        receive(paillier.decrypt(c));
    }

    public void send(Speaker receiver, BigInteger m) {
        System.out.println(name + " -> " + receiver.name + " : " + m);
        receiver.receive(m);
    }

    public void sendPaillier(Speaker receiver, BigInteger m) {
        BigInteger c = paillier.encrypt(m);
        System.out.println(name + " -> " + receiver.name + " : " + c);
        receiver.receivePaillier(c, paillier);
    }

}
