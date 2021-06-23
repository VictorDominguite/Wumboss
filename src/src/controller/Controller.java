package src.controller;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import src.model.actions.IActionAgent;
import src.model.actions.IActionCreator;
import src.model.actions.IActionParser;
import src.utils.exceptions.TipoDeSalaInvalido;

public class Controller implements IController{
	private String dataPath;
	private String assetsPath;
	
	private CSVHandler fileIO;
	private KeyboardHandler keyboard;
	private ViewHandler view;
	
	private IActionParser actionHandler;
	
	public Controller(String dataPath, String assetsPath) {
		if(dataPath == null || assetsPath == null) {
			System.err.println("Data e assets paths não podem ser nulos!");
			throw new RuntimeException();
		}
		
		if(!dataPath.endsWith("/")) dataPath += "/";
		if(!assetsPath.endsWith("/")) assetsPath += "/";
		
		this.dataPath = dataPath;
		this.assetsPath = assetsPath;
		
		this.fileIO = new CSVHandler();
		this.keyboard = new KeyboardHandler(this);
		this.view = new ViewHandler(this);
	}
	
	public String[][] readSala(int tipo) throws TipoDeSalaInvalido, IOException {
		String[][] res;
		String path;
		
		if(tipo < 10)
			path = dataPath + "sala0" + tipo + ".csv";
		else
			path = dataPath + "sala" + tipo + ".csv";
		
		try {
			res = fileIO.readCSV(path);
		} catch(FileNotFoundException e) {
			throw new TipoDeSalaInvalido(tipo);
		}
		
		if(res == null) 
			throw new IOException();
		
		return res;
	}
	
	public BufferedImage readIcon(String name){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(assetsPath + name + ".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}

	public void sendMessage(String message) {
		if(actionHandler != null)
			actionHandler.sendMessage(message);
	}
	
	@Override
	public void connect(String name, IActionAgent agent) {
		if(name.equals("parser") && agent instanceof IActionParser)
			this.actionHandler = (IActionParser) agent;
	}

	public void setKeyboardMappings(InputMap im, ActionMap am) {
		for(char s : KeyboardHandler.keysToListen) {
			im.put(KeyStroke.getKeyStroke(s), s);
			am.put(s, this.keyboard);
		}
	}
	
}
