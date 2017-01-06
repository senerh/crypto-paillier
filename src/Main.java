import paillier.Paillier;
import speaker.Speaker;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
        Paillier paillier = new Paillier();

        BigInteger m1 = new BigInteger("2");
        BigInteger m2 = new BigInteger("5");
        BigInteger m3 = m1.add(m2);

		BigInteger c1 = paillier.encrypt(m1);
        BigInteger c2 = paillier.encrypt(m2);

        BigInteger c3 = c1.multiply(c2);

        BigInteger _m3 = paillier.decrypt(c3);

        System.out.println("m1 : " + m1);
        System.out.println("m2 : " + m2);
        System.out.println("m3 : " + m3);
        System.out.println("c1 : " + c1);
        System.out.println("c2 : " + c2);
        System.out.println("c3 : " + c3);
        System.out.println("_m3 : " + _m3);

        if (_m3.equals(m3)) {
            System.out.println("Mais wolalala ça marche");
        } else {
            System.out.println("On s'y attendait....");
        }
	}

}