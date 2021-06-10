package src.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVHandler {
	//TODO: Criar readCSV que cria um vetor de chars, mover responsabilidade de tornar em matriz
	//		de Strings para o Controller
	public String[][] readCSV(String path) throws FileNotFoundException{
		String[][] result = null;
		try(var br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			String[] tamanhos = line.split(",");
			
			if(tamanhos.length != 2 || !tamanhos[0].equals(tamanhos[1])) 
				throw new FileNotFoundException("As duas dimensões devem ser iguais!");
			
			int tamanho = Integer.parseInt(tamanhos[0]);
			
			result = new String[tamanho][];
			for(int i = 0; i < tamanho; i++) {
				result[i] = br.readLine().split(",");
			}
			
		} catch(IOException e) {
			System.err.println("Houve algum erro de I/O: " + e.getMessage());
		} catch(NumberFormatException e) {
			System.err.println("As duas dimensões devem ser números inteiros!");
		}
		
		return result;
	}
}