package src.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import src.utils.Constantes;
import src.utils.actions.IActionParser;
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
		
		int modelo = Constantes.rng.nextInt(3) + 1;

		if(tipo < 10)
			path = dataPath + "tipo0" + tipo + "/sala0" + modelo + ".csv";
		else
			path = dataPath + "tipo" + tipo + "/sala" + modelo + ".csv";
		
		try {
			res = fileIO.readCSV(path);
		} catch(FileNotFoundException e) {
			throw new TipoDeSalaInvalido(tipo);
		}
		
		if(res == null) 
			throw new IOException();
		
		return res;
	}
	
	public BufferedImage readIcon(String name) throws IOException{
		BufferedImage img = ImageIO.read(new File(assetsPath + name + ".png"));
		
		return img;
	}
	
	public File hackFontFile(String mode) {
		try {
			File f = new File(assetsPath + "ttf/Hack-" + mode + ".ttf");
			return f;
		} catch(NullPointerException e) {
			System.err.println("Unable to open font file " + e.getMessage());
		}
		
		return null;
	}

	public void sendMessage(String message) {
		if(actionHandler != null)
			actionHandler.sendMessage(message);
	}
	
	public void connect(IActionParser agent) {
		this.actionHandler = (IActionParser) agent;
	}

	public void setKeyboardMappings(InputMap im, ActionMap am) {
		for(char s : KeyboardHandler.keysToListen) {
			im.put(KeyStroke.getKeyStroke(s), s);
			am.put(s, this.keyboard);
		}
	}

	public void setButtonMappings(JButton b) {
		b.addActionListener(view);
	}

	public void disconnect() {
		this.actionHandler = null;
		
	}
	
}
