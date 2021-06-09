package src.controller;

import java.io.IOException;

import src.utils.exceptions.TipoDeSalaInvalido;

public interface IController {
	public String[][] readSala(int tipo) throws TipoDeSalaInvalido, IOException;
	
	
}
