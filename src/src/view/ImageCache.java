package src.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;

import src.controller.IController;

public abstract class ImageCache {
	private static HashMap<String, ImageIcon> icons = new HashMap<String, ImageIcon>(50);
	
	private static IController ioHandler;
	
	public static void insertIconInCache(String name, ImageIcon icon) {
		icons.put(name, icon);
	}
	
	public static void setIOHandler(IController io) {
		ImageCache.ioHandler = io;
	}
	
	public static ImageIcon getIcon(String name, int width, int height) {
		ImageIcon icon = icons.get(name);
		
		if(icon == null) {
			try {
				BufferedImage buffImg = ioHandler.readIcon(name);
				icon = new ImageIcon(new ImageIcon(buffImg).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
				
				ImageCache.insertIconInCache(name, icon);
			} catch(IOException e) {
				System.err.println("Não foi possivel abrir a imagem de nome: " + name);
			}
		}
		
		return icon;
	}
}
