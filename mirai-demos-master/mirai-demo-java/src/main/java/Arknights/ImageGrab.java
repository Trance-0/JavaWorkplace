package Arknights;

import java.io.File;
import java.util.Random;

public class ImageGrab {
	private Random rand = new Random();
	private String path;
	public ImageGrab(){
		if( System.getProperty("os.name").contains("Mac")) {
			path = System.getProperty("user.dir") + "/Resources/Images/";
		}else {
			path = System.getProperty("user.dir") + "\\Resources\\Images\\";
		}
	}

	public String getAddress() {
		int index = rand.nextInt(36);
		if (index > 0) {
//			File file = new File(localPath + i + ".png");
			File file = new File(
					path+"(" + Integer.toString(index) + ").PNG");
			try {
				if (file.exists()) {
//				return localPath + i + ".png";
					return file.toString();
				} else {
					return "Image Not Found" + file.toString();
				}
			} catch (Exception e) {
				file = new File(
						path+"(" + Integer.toString(index) + ").JPG");

				if (file.exists()) {
//					return localPath + i + ".png";
					return file.toString();
				} else {
					System.out.print("Failed to load the data");
					return null;
				}
			}
		} else {
			throw new java.lang.IllegalArgumentException();
		}
	}

}
