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
	private static GameView view;
	
	public static void insertIconInCache(String name, ImageIcon icon) {
		icons.put(name.toLowerCase(), icon);
	}
	
	public static void setIOHandler(IController io) {
		ImageCache.ioHandler = io;
	}
	
	public static void setGameView(GameView view) {
		ImageCache.view = view;
	}
	
	public static ImageIcon getIcon(String name, int width, int height) {
		if(name.equalsIgnoreCase("heroi"))
			name = ImageCache.getNameHeroi();
		
		String nameLower = name.toLowerCase();
		ImageIcon icon = icons.get(nameLower);
		
		if(icon == null) {
			try {
				BufferedImage buffImg = ioHandler.readIcon(name);
				icon = new ImageIcon(new ImageIcon(buffImg).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
				
				ImageCache.insertIconInCache(nameLower, icon);
			} catch(IOException e) {
				System.err.println("Não foi possivel abrir a imagem de nome: " + name);
			}
		}
		
		return icon;
	}
	
	private static String getNameHeroi() {
		if(view == null)
			return "Heroi";
		
		String res = "Heroi";
		String[] heroState = view.getGameModel().getHeroState("state");
		
		for(int i = 0; i < heroState.length; i++) {
			if(!heroState[i].equals("null"))
				res += "_" + heroState[i].toLowerCase();
		}
		
		return res;
	}
}
