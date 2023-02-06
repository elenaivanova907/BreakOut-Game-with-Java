package parts;

import javax.swing.ImageIcon;

public class Brick extends Common{
	
	
	private boolean destroyed;
	
	public Brick(int x, int y) {
        
        initBrick(x, y);
    }
    
    private void initBrick(int x, int y) {
        
        this.x = x;
        this.y = y;
        
        destroyed = false;

        loadImage();
        getImageDimensions();
    }
    

	public void setDestroyed(boolean d) {
		destroyed = d;
	}
	boolean destroyed() {
		return destroyed;
	}
	private void loadImage() {
        
        var ii = new ImageIcon("src/resources/brick.png");
        image = ii.getImage();        
    }
	
}
