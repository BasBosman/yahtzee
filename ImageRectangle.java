package gamedesign; 
import org.newdawn.slick.Image;

//image rectangle class which can detect mouse collision

public class ImageRectangle {
	private int minx;
	private int miny;
	private int maxx;
	private int maxy;
	private Image img;
	ImageRectangle(Image img, int imx, int imy){
		this.img = img;
		minx = imx;
		miny = imy;
		maxx = imx + img.getWidth();
		maxy = imy + img.getHeight();
	}
	//boolean returns true if mouse is hovering over image.
	public boolean checkCollision(int mousex, int mousey) {
		if (mousex > minx && mousex < maxx && mousey > miny && mousey < maxy) {
			return true;
		} else {return false;}
	}
	//check if collides with another imagerect (at least 1 corner must be inside)
	public boolean checkCollision(ImageRectangle imgrect) {
		int[] locs = imgrect.getLocation();
		if (locs[0] > minx && locs[0] < maxx && locs[1] > miny && locs[1] < maxy) {return true;} 		
		if (locs[0] > minx && locs[0] < maxx && locs[3] > miny && locs[3] < maxy) {return true;}
		if (locs[2] > minx && locs[2] < maxx && locs[1] > miny && locs[1] < maxy) {return true;} 		
		if (locs[0] > minx && locs[0] < maxx && locs[3] > miny && locs[3] < maxy) {return true;}
		return false;
	}
	//check if another imgrect is inside (all 4 corners must be inside)
	public boolean checkInisde(ImageRectangle imgrect) {
		int[] locs = imgrect.getLocation();
		if (locs[0] > minx && locs[0] < maxx && locs[1] > miny && locs[1] < maxy &&	
			locs[0] > minx && locs[0] < maxx && locs[3] > miny && locs[3] < maxy &&
			locs[2] > minx && locs[2] < maxx && locs[1] > miny && locs[1] < maxy &&	
			locs[0] > minx && locs[0] < maxx && locs[3] > miny && locs[3] < maxy) {return true;}
		return false;
	}
	//this static method draws a new image and assigns a 
	public static ImageRectangle drawRect(Image img, int x, int y) {
		img.draw(x, y);
		return new ImageRectangle(img, x, y);
	}
	//overloaded method that draws existing image rect
	public void drawRect() {
		img.draw(minx, miny);
	}
	public void setLocation(int x, int y) {
		minx = x;
		miny = y;
		maxx = minx + img.getWidth();
		maxy = maxx + img.getHeight();
		img.draw(minx, miny);
	}
	public void setImage(Image img) {
		this.img = img;
		maxx = minx + img.getWidth();
		maxy = maxx + img.getHeight();
	}
	public int[] getLocation() {
		int[] A = {minx, miny, maxx, maxy};
		return A;
	}
	public Image getImage() {
		return img;
	}
}
