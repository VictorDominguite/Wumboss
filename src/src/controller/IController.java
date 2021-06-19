package src.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import src.utils.exceptions.TipoDeSalaInvalido;

public interface IController {
	public String[][] readSala(int tipo) throws TipoDeSalaInvalido, IOException;
	public BufferedImage readIcon(String name);
}
