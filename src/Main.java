import rsa.RSA;
import speaker.Speaker;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		Speaker speakerBob = new Speaker("Bob", new RSA());
		Speaker speakerAlice = new Speaker("Alice", new RSA());

		speakerBob.sendEncrypted(speakerAlice, new BigInteger("1994"));
	}

}
