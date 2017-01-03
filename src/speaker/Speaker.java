package speaker;

import paillier.InterfacePaillier;

import java.math.BigInteger;
import java.util.Random;

public class Speaker {

    private String name;
    private InterfacePaillier paillier;
    private BigInteger m;
    private Random random;

    public Speaker(String name, InterfacePaillier paillier) {
        this.name = name;
        this.paillier = paillier;
        this.random = new Random();
    }

    public void receive(BigInteger m) {
        System.out.println(name + " received : " + m);
        this.m = m;
    }

    private void receivePaillier(BigInteger c, BigInteger sk, BigInteger n) {
        receive(decrypt(c, sk, n));
    }

    public void send(Speaker receiver, BigInteger m) {
        System.out.println(name + " -> " + receiver.name + " : " + m);
        receiver.receive(m);
    }

    public void sendPaillier(Speaker receiver, BigInteger m) {
        BigInteger encryptedM = encrypt(m, paillier.getN());
        System.out.println(name + " -> " + receiver.name + " : " + encryptedM);
        receiver.receivePaillier(encryptedM, paillier.getSK(), paillier.getN());
    }

    private BigInteger encrypt(BigInteger m, BigInteger n) {
        BigInteger n_p1 = n.add(BigInteger.ONE);
        BigInteger n_2 = n.multiply(n);
        BigInteger n_p1_exp_m = n_p1.modPow(m, n_2);
        BigInteger r = randomBigInteger(n);
        BigInteger r_exp_n = r.modPow(n, n_2);
        BigInteger c = n_p1_exp_m.multiply(r_exp_n).mod(n_2);
        return c;
    }

    private BigInteger decrypt(BigInteger c, BigInteger sk, BigInteger n) {
        BigInteger n_inv = n.modInverse(sk);
        BigInteger r = c.modPow(n_inv, n);
        BigInteger n_2 = n.multiply(n);
        BigInteger m = c.multiply(r.modPow(n.negate(), n_2)).mod(n_2).subtract(BigInteger.ONE).divide(n);
        return m;
    }

    private BigInteger randomBigInteger(BigInteger n) {
        int maxNumBitLength = n.bitLength();
        BigInteger aRandomBigInt;
        do {
            aRandomBigInt = new BigInteger(maxNumBitLength, random);
        } while (aRandomBigInt.compareTo(n) > 0);
        return aRandomBigInt;
    }

}
