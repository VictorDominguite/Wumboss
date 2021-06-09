package src.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.utils.exceptions.TipoDeSalaInvalido;

public class Controller implements IController{
	private String dataPath;
	private String assetsPath;
	
	private CSVHandler fileIO;
	
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

}
