import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class CropImageTest {
	BufferedImage image;
	BufferedImage patches[];

	int PATCHSIZE = 8;  //patch is rectangle with width and heights both 8px.
	
	
	public CropImageTest(File file) throws Exception {
		FileInputStream fIS = new FileInputStream(file);
		image = ImageIO.read(fIS);	
	}
	
	private void crop(){
		int h = image.getHeight();
		int w = image.getWidth();
		
		// the patches overlap each other
		int patchNumberVertical = h - 8 + 1;
		int patchNumberHorizontal = w - 8 +1;
		int numberOfPatches = patchNumberVertical * patchNumberHorizontal;
	}
	
	
	
	
	
	
	
}
