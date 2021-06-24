package src.view;

import java.util.HashMap;

import javax.swing.ImageIcon;

public abstract class ImageCache {
	private static HashMap<String, ImageIcon> images = new HashMap<String, ImageIcon>(50);
	
	public static void insertIconInCache(String name, ImageIcon icon) {
		images.put(name, icon);
	}
	
	public static ImageIcon getIconFromCache(String name) {
		return images.get(name);
	}
}
