package yahtzee;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList; 

public class Yahtzee extends BasicGame
{
	public Yahtzee(String gamename)
	{
		super(gamename);
	}

	int[] emptyarray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	ArrayList<Image> img = new ArrayList<>();
	boolean setup = false;
	Image imgx;
	String folder;
	int mode = 1;
	int numberPlayers;
	int numberGames;
	int playerturn = 0;
	String input = "";
	int rerollcount = 0;
	
	ArrayList<Speler> spelers = new ArrayList<>();
	Worp worp = new Worp();
	String[] yahtzeeoptions = {"Enen", "Tweeën", "Drieën", "Vieren", "Vijfen", "Zessen", "Totaal Boven" ,"Bonus", "","3 of a Kind", "Carré", "Full House", "Kleine Straat", "Grote Straat", "Yahtzee", "Chance", "","Totaal"};
	int selection = 0;
	int[] addPts;
	int selecter= 0;
	
	//int ７zeven = 7;
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		folder = "C:\\yahtzee\\";
		for (int i = 1; i < 7; i++) {
			img.add(new Image(folder+"dice" + i + ".png"));
		}
		imgx = new Image(folder+"x.png");
	}
	
	public String str = "";
	int i = 0;

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.drawString(str, 50, 50);
		//i++;
		//g.drawString(Integer.toString(i), 50, 150);
		//for (int i = 1; i < 7; i++) {
		//	img.get(i).draw(10 + 60*i, 10);
		//}
		switch(mode) {
		case(1):	
			g.drawString("Vul aantal spelers in: (max 10)", 50, 50);
			g.drawString(input, 50, 100);
			break;
		case(2):
			g.drawString("Vul aantal spellen in: (max 5)", 50, 50);
			g.drawString(input, 50, 100);
			break;
		case(3):
			g.drawString("Speler " + (playerturn+1) + ", vul uw naam in.", 50, 50);
			g.drawString(input, 50, 100);
			break;
		case(4):
			g.drawString(spelers.get(playerturn).naam + ", dit is uw worp.", 50, 50);
			g.drawString("Selecteer welke dobbelstenen u wilt behouden.", 50, 65);
			for (int j = 0; j < 5; j++) {
				img.get(worp.worp.get(j).waarde).draw(10 + 60*j, 100);
				if (worp.worp.get(j).hold) {
					imgx.draw(10 + 60*j, 100);
				}
			}
			break;
		case(5):
			g.drawString(spelers.get(playerturn).naam + ", dit is uw worp.", 50, 50);
			g.drawString("Selecteer waar u uw punten wilt schrijven.", 50, 65);
			for (int j = 0; j < 5; j++) {
				img.get(worp.worp.get(j).waarde).draw(40 + 60*j, 100);
			}
			setup = true;
			break;
		}
		if (setup) {
			for (int j = 0; j < 18; j++) {
				g.drawString(yahtzeeoptions[j], 25, 150 + 15*j);
			}
			for (int j = 0; j < 6; j++) {
				g.drawString(Integer.toString(addPts[j]), 150, 150 + 15*j);
			}
			for (int j = 6; j < 13; j++) {
				g.drawString(Integer.toString(addPts[j]), 150, 195 + 15*j);
			}
			if (selecter < 6) {
				g.drawString(">", 10, 150 + 15*selecter);
			} else {g.drawString(">", 10, 195 + 15*selecter);}
			for (int i = 0; i < numberGames; i++) {
				for (int j = 0; j < 16; j++) {
					int printpos = (j < 6 ? j : j + 3);
					try {
					g.drawString(Integer.toString( spelers.get(playerturn).score.get(j).get(i) 
							), 195 + 45*i, 150 + 15*printpos);
					} catch (IndexOutOfBoundsException exception) {
						g.drawString("-", 195 + 45*i, 150 + 15*printpos);
					}
				}
			}
			g.drawString("Grand Total:",500,135);
			for (int i = 0; i < numberPlayers; i++) {
				g.drawString(spelers.get(i).naam,500,150 + 15*i);
				g.drawString(Integer.toString(spelers.get(i).grandTotal),600,150 + 15*i);
			}

		}

	}

	public static void main(String[] args)

	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Yahtzee("Yahtzee"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Yahtzee.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

		/**
		 * @see org.newdawn.slick.BasicGame#mouseClicked(int, int, int, int)
		 */
		public void keyPressed(int key, char c) {
			//input = Integer.toString(key); //backspace = 14, enter = 28
			//1 <== 2, 2 <==3, 3 <==4, 4 <==5
			//up = 200, right = 205, down = 208, left = 203
			if (mode <= 3) {
				if (key == 14) {
					input = "";
				}
				else if (key == 28) {
					try {
						if (mode == 1) {numberPlayers = getOutput(input, 11);}
						else if (mode == 2) {numberGames = getOutput(input, 6);}
						if (mode != 3) { mode += 1;}
						else {
							spelers.add(new Speler(input));
							if (playerturn == numberPlayers - 1) {
								mode += 1;
								playerturn = 0;
								playGame1();
							} else {playerturn += 1;}
						}
						input = "";
					} catch (Exception e) {
						input = "Voer aub een geldig getal in";
					}
				}
				else {input += c;}
			}
			else if (mode == 4) {
				if (key > 1 && key < 7) {
					worp.worp.get(key - 2).hold = !worp.worp.get(key - 2).hold; //toggle hold boolean
				}
				else if (key == 28) {
					playReroll();
					if (rerollcount == 2) {
						mode = 5;
						calcPts();
					}
				}
			}
			else if (mode == 5) {
				if (key == 200 && selecter > 0) {
					selecter -= 1;
				}
				else if (key == 208 && selecter < 12) {
					selecter += 1;
				}
				else if (key == 28 && spelers.get(playerturn).score.get(selecter).size() < numberGames) {//&&check if room in selected  
					spelers.get(playerturn).score.get(selecter).add(addPts[selecter]);
					spelers.get(playerturn).calcTotal(numberGames);
					if (playerturn == numberPlayers - 1) {
						playerturn = 0;
					} else {playerturn += 1;}					
					mode = 4;
					playGame1();
				}
			}
			
			
		}
	
		public void mouseClicked(int button, int x, int y, int clickCount) {
			if (clickCount == 1) {
				str = "Single Click: "+button+" "+x+","+y;
			}
			if (clickCount == 2) {
				str = "Double Click: "+button+" "+x+","+y;
			}
		}
		int getOutput(String in, int max) {
			int out = Integer.parseInt(in);
			if (out < max) {
				return out;
			} else throw new ArithmeticException("Please enter valid number");
		}
		void playGame1() {
			rerollcount = 0;
			worp.roll();
			addPts = worp.calcPts();
		}
		void playReroll() {
			rerollcount += 1;
			worp.reroll();
			addPts = worp.calcPts();
		}
		void calcPts( ) {
			//addPts = worp.calcPts();
		}
		void switchPlayer() {
			if (playerturn == numberPlayers - 1) {
				playerturn = 0;
			} else {playerturn += 1;}
		}
	}
//⚀ ⚁ ⚂ ⚃ ⚄ ⚅