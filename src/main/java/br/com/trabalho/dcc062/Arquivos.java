package br.com.trabalho.dcc062;

import java.util.ArrayList;
import java.util.List;

public class Arquivos {

	private static List<Editor> arquivos = new ArrayList<Editor>();
	private static Editor editor;

	public Arquivos() {
		arquivos.add(new Editor());
		editor = arquivos.get(0);
	}
	
	public static Editor getEditor() {
		return editor;
	}
	
	public static void setEditor(Editor editorNovo) {
		editor = editorNovo;
	}

	public static Editor get(int index) {
		return arquivos.get(index - 1);
	}
	
	public static void add(Editor editorNovo) {
		arquivos.add(editorNovo);
		editor = editorNovo;
	}
	
	public static int size() {
		return arquivos.size();
	}

}
