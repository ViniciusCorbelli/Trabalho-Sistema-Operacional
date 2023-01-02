package br.com.trabalho.dcc062.editor;

import java.util.ArrayList;
import java.util.List;

public class Arquivos {

	private static List<Editor> arquivos = new ArrayList<Editor>();
	private static Editor editorAberto;

	public Arquivos() {
		arquivos.add(new Editor());
		editorAberto = arquivos.get(0);
	}
	
	public static Editor getEditor() {
		return editorAberto;
	}
	
	public static void setEditor(Editor editorNovo) {
		editorAberto = editorNovo;
	}

	public static Editor get(int index) {
		return arquivos.get(index - 1);
	}
	
	public static void add(Editor editorNovo) {
		arquivos.add(editorNovo);
		editorAberto = editorNovo;
	}
	
	public static int size() {
		return arquivos.size();
	}

}
