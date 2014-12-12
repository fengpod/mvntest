import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class GifImageSize {
	public static void main(String[] args) {
		File file = new File("d:/1.gif");
		try {
			// 设置原图尺寸
			BufferedImage oImage = ImageIO.read(file);
			
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			JPEGImageEncoder encoder =
			com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(byteOut);
			encoder.encode(oImage); 
			byte[] bytes = byteOut.toByteArray();
//			System.out.println(byteOut.);
			System.out.println(oImage.getHeight());
		}
		catch(Exception e){
			
		}
	}
}
