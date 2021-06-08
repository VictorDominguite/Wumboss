package src.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class CSVHandler {
	public static String[][] readCSV(String path) {
		String[][] result = null;
		try(var br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			String[] tamanhos = line.split(",");
			if(tamanhos.length != 2 || tamanhos[0] != tamanhos[1]) 
				throw new IOException("As duas dimensões devem ser iguais!");
			
			int tamanho = Integer.parseInt(tamanhos[0]);
			
			result = new String[tamanho][];
			for(int i = 0; i < tamanho; i++) {
				result[i] = br.readLine().split(",");
			}
			
		} catch(FileNotFoundException e) {
			System.err.println("Não foi possível abrir o arquivo!");
		} catch(IOException e) {
			System.err.println("Houve algum erro de I/O: " + e.getMessage());
		} catch(NumberFormatException e) {
			System.err.println("As duas dimensões devem ser números inteiros!");
		}
		
		if(result == null)
			System.err.println("Houve algum erro inesperado ao ler o CSV em " + path);
		
		return result;
	}
}