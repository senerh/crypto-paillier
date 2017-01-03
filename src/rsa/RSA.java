package rsa;

import java.math.BigInteger;
import java.util.Random;

public class RSA implements InterfaceRSA {

    private static int KEY_SIZE = 512;

    private Random random;

    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi_n;
    private BigInteger e;
    private BigInteger d;
	
	public RSA() {
		random = new Random();
		init();
	}

	private void init() {
        generateP();
        generateQ();
        generateN();
        generatePhiN();
        generateE();
        generateD();
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

    private void generateE() {
		e = new BigInteger(16, 300, random);
		while (!isPrimal(e, phi_n)) {
			e = e.nextProbablePrime();
		}
	}
	
	private void generateD() {
		d = e.modInverse(phi_n);
	}

	public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
	
	private boolean isPrimal(BigInteger a, BigInteger b) {
		if (a.equals(BigInteger.ZERO))
			return false;
		if (a.equals(BigInteger.ONE))
			return true;
		return isPrimal(b.mod(a), b);
	}

    public BigInteger getN() {
        return n;
    }
}
