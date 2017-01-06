package paillier;

import java.math.BigInteger;
import java.util.Random;

public class Paillier {

    private static int KEY_SIZE = 512;

    private Random random;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi_n;
    private BigInteger e;
    private BigInteger d;

    public Paillier() {
        random = new Random();
        init();
    }

    private void init() {
        generateP();
        generateQ();
        generateN();
        generatePhiN();
    }

    private void generateP() {
        p = new BigInteger(KEY_SIZE, 30, random);
    }

    private void generateQ() {
        q = new BigInteger(KEY_SIZE, 30, random);
    }

    private void generateN() {
        n = p.multiply(q);
    }

    private void generatePhiN() {
        BigInteger p_m1 = p.subtract(BigInteger.ONE);
        BigInteger q_m1 = q.subtract(BigInteger.ONE);
        phi_n = p_m1.multiply(q_m1);
    }

    public BigInteger getPK() {
        return n;
    }

    public BigInteger getSK() {
        return phi_n;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger encrypt(BigInteger m) {
        BigInteger n_p1 = n.add(BigInteger.ONE);
        BigInteger n_2 = n.multiply(n);
        BigInteger n_p1_exp_m = n_p1.modPow(m, n_2);
        BigInteger r = randomBigInteger(n);
        BigInteger r_exp_n = r.modPow(n, n_2);
        BigInteger c = n_p1_exp_m.multiply(r_exp_n).mod(n_2);
        return c;
    }

    public BigInteger decrypt(BigInteger c) {
        BigInteger n_inv = n.modInverse(phi_n);
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
