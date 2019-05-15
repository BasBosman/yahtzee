package gamedesign; 
import org.newdawn.slick.Image;

//image rectangle class which can detect mouse collision

public class ImageRectangle {
	int minx;
	int miny;
	int maxx;
	int maxy;
	ImageRectangle(Image img, int imx, int imy){
		minx = imx;
		miny = imy;
		maxx = imx + img.getWidth();
		maxy = imy + img.getHeight();
	}
	public boolean checkCollision(int mousex, int mousey) {
		if (mousex > minx && mousex < maxx && mousey > miny && mousey < maxy) {
			return true;
		} else {return false;}
	}
	public static ImageRectangle drawRect(Image img, int x, int y) {
		img.draw(x, y);
		return new ImageRectangle(img, x, y);
	}
	
}
