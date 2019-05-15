package yahtzee;
import java.util.ArrayList; 
import java.util.Random;
import java.util.Collections;

public class Worp {
	ArrayList<Dobbelsteen> worp = new ArrayList<>();
	
	void roll() {
		Random r = new Random();
		worp.clear();
		for (int i = 0; i < 5; i++) {
			worp.add(new Dobbelsteen(r.nextInt(6)));
		}
	}
	void reroll() {
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			if (worp.get(i).hold == true) {
				worp.set(i, new Dobbelsteen(r.nextInt(6)));
			}
		}
	}
	int[] calcPts() {
		ArrayList<Integer> countnumbers = new ArrayList<>();
		countnumbers.add(0); //enen
		countnumbers.add(0); //tweeen
		countnumbers.add(0); //drieen
		countnumbers.add(0); //vieren
		countnumbers.add(0); //vijfen
		countnumbers.add(0); //zessen
		for (int i = 0; i < 5; i++) {
			int aantalogen = worp.get(i).waarde;
			countnumbers.set(aantalogen , countnumbers.get(aantalogen ) + 1);
		}
		int som =
		 		  countnumbers.get(0)+ //enen
				2*countnumbers.get(1)+ //tween
				3*countnumbers.get(2)+ //drien
				4*countnumbers.get(3)+ //vieren
				5*countnumbers.get(4)+ //vijfen
				6*countnumbers.get(5); //zessen
		boolean theeoakind = (Collections.max(countnumbers) >= 3); //(countnumbers.get(0) >= 3 || countnumbers.get(1) >= 3 || countnumbers.get(2) >= 3 || countnumbers.get(3) >= 3 || countnumbers.get(4) >= 3 || countnumbers.get(5) >= 3);
		boolean fouroakind = (Collections.max(countnumbers) >= 4); //|| countnumbers.get(1) >= 4 || countnumbers.get(2) >= 4 || countnumbers.get(3) >= 4 || countnumbers.get(4) >= 4 || countnumbers.get(5) >= 4);
		boolean hasYahtzee = (Collections.max(countnumbers) == 5); //(countnumbers.get(0) == 5 || countnumbers.get(1) == 5 || countnumbers.get(2) == 5 || countnumbers.get(3) == 5 || countnumbers.get(4) == 5 || countnumbers.get(5) == 5);
		boolean hasBigStrt = (Collections.max(countnumbers) == 1 && (countnumbers.get(0) == 0 || countnumbers.get(5) == 0));
		boolean hasSmalStr = ((countnumbers.get(2) >= 1 && countnumbers.get(3) >= 1) &&							//kleine straat heeft altijd een 3 en een 4 nodig
						((countnumbers.get(1) >=1 && (countnumbers.get(0) >=1 || countnumbers.get(4) >= 1) ) || 	//met daarbij een 2 en een 1 of een 5
						(countnumbers.get(4) >=1 && countnumbers.get(5) >= 1))); //of een 5 met een 6
		boolean FullHouse = (Collections.max(countnumbers) == 3 && (
				countnumbers.get(0) == 2 ||
				countnumbers.get(1) == 2 ||
				countnumbers.get(2) == 2 ||
				countnumbers.get(3) == 2 ||
				countnumbers.get(4) == 2 ||
				countnumbers.get(5) == 2 ));
		int[] returnlist = {
		 		  countnumbers.get(0), //enen
				2*countnumbers.get(1), //tween
				3*countnumbers.get(2), //drien
				4*countnumbers.get(3), //vieren
				5*countnumbers.get(4), //vijfen
				6*countnumbers.get(5), //zessen
				theeoakind ? som : 0,
				fouroakind ? som : 0,
				FullHouse  ?  25 : 0,
				hasSmalStr ?  30 : 0,
				hasBigStrt ?  40 : 0,
				hasYahtzee ?  50 : 0,
				som};
		return returnlist;
	}
}