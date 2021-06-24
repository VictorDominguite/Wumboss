package src.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ActionMap;
import javax.swing.InputMap;

import src.utils.actions.IActionCreator;
import src.utils.exceptions.TipoDeSalaInvalido;

public interface IController extends IActionCreator{
	public String[][] readSala(int tipo) throws TipoDeSalaInvalido, IOException;
	public BufferedImage readIcon(String name) throws IOException;
	
	public void setKeyboardMappings(InputMap im, ActionMap am);
}
