package src.controller;

import src.utils.exceptions.TipoDeSalaInvalido;

public interface IController {
	public String[][] readSala(int tipo) throws TipoDeSalaInvalido;
}
