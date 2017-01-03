import paillier.Paillier;
import speaker.Speaker;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		Speaker speakerBob = new Speaker("Bob", new Paillier());
		Speaker speakerAlice = new Speaker("Alice", new Paillier());

		speakerBob.sendPaillier(speakerAlice, new BigInteger("36200"));
	}

}
