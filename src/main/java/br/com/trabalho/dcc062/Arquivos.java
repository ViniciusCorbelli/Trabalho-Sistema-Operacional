package br.com.trabalho.dcc062;

import java.util.ArrayList;
import java.util.List;

public class Arquivos {

	private static Arquivos singleton;
	private List<Editor> arquivos = new ArrayList<Editor>();
	private Editor editor;

	public static synchronized Arquivos getInstance() {
		if (singleton == null)
			singleton = new Arquivos();
		return singleton;
	}

	public Arquivos() {
		arquivos.add(new Editor());
		editor = arquivos.get(0);
	}
	
	public Editor getEditor() {
		return this.editor;
	}
	
	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public Editor get(int index) {
		return arquivos.get(index - 1);
	}
	
	public void add(Editor editor) {
		arquivos.add(editor);
		this.editor = editor;
	}
	
	public int size() {
		return arquivos.size();
	}

}
