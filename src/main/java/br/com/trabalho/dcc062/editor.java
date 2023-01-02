package br.com.trabalho.dcc062;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Editor {

	private List<String[]> editor = new ArrayList<String[]>();
	private int QTD_LINHAS = 0;
	public static final int MAX_COLUNAS = 20;
	
	public Editor() {
	}
	
	public String getTexto(int linha, int coluna) throws Exception {
		if (linha > QTD_LINHAS || linha < 1) {
			throw new Exception("Não é possível acessar está linha.");
		}
		
		if (coluna > MAX_COLUNAS || coluna < 1) {
			throw new Exception("Não é possível acessar está coluna.");
		}
		
		return editor.get(linha - 1)[coluna - 1];
	}
	
	public void setTexto(int linha, int coluna, String palavra) {
		verificaLinhas(linha);
		
		editor.get(linha - 1)[coluna - 1] = palavra;
	}
	
	private void verificaLinhas(int linha) {
		if (linha > QTD_LINHAS) {
			for (int i = QTD_LINHAS; i < linha; i++) {
				editor.add(new String[MAX_COLUNAS]);
			}
			QTD_LINHAS = linha;
		}
	}
	
	public void verDocumento() {
		for (String[] linhas : editor) {
			for (String texto : linhas) {
				if (texto != null) {
					System.out.print(texto + " ");
				}
			}
			System.out.println();
		}
	}

	public void salvarDocumento(String diretorio) throws Exception {
		FileWriter arq = new FileWriter(diretorio);
		try (PrintWriter printArq = new PrintWriter(arq)) {
			for (String[] linhas : editor) {
				for (String texto : linhas) {
					if (texto != null) {
						printArq.print(texto + " ");
					}
				}
				printArq.println();
			}
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getCause());
		}
	}

}
