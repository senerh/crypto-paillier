package paillier;

import java.math.BigInteger;
import java.util.Random;

public class Paillier implements InterfacePaillier {

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

    @Override
    public BigInteger getPK() {
        return n;
    }

    @Override
    public BigInteger getSK() {
        return phi_n;
    }

    @Override
    public BigInteger getN() {
        return n;
    }
}
