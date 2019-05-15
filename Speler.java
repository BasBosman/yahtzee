package yahtzee;
import java.util.ArrayList;

public class Speler {
	String naam;
	
	Speler(String naam) {
		this.naam = naam;
	}
	
	ArrayList<ArrayList<Integer>> score = new ArrayList<ArrayList<Integer>>(); {
	for (int i = 0; i < 16; i++) {
		score.add(new ArrayList<Integer>());
		//13 = total boven
		//14 = bonus
		//15 = totaal
		}
	}
	int grandTotal;
	void calcTotal(int gamelength) {
		score.get(13).clear();
		score.get(14).clear();
		score.get(15).clear();
		for (int k =  0; k < gamelength; k++) {
			score.get(13).add(0);
			score.get(14).add(0);
			score.get(15).add(0);
		}
		for (int i = 0; i<gamelength; i++) {
			for (int j = 0; j<6; j++) {
				score.get(13).set(i, score.get(13).get(i) + getPoints(j, i));
			}
			if (score.get(13).get(i) >= 63) {
				score.get(14).set(i,35);
			}
			for (int j = 6; j<15; j++) {
				score.get(15).set(i, score.get(15).get(i) + getPoints(j, i));
			}
		}
		grandTotal = 0;
		for(int d : score.get(15)) {
		    grandTotal += d;
		}
	}
	int getPoints(int i, int j) {
		try {
			return score.get(i).get(j);
		} catch(IndexOutOfBoundsException exception) {return 0;}
	}
}