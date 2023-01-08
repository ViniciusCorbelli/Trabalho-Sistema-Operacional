package br.com.trabalho.dcc062.menu.acoes;

import java.util.Scanner;

import br.com.trabalho.dcc062.editor.Arquivos;
import br.com.trabalho.dcc062.editor.Editor;
import br.com.trabalho.dcc062.menu.Menu;
import br.com.trabalho.dcc062.menu.Opcao;

public class Inicio {

	private Scanner scanner = new Scanner(System.in);
	
	public Inicio() {
	}

	public boolean sair() {
		return false;
	}

	public boolean abrirArquivo() {
		Menu menu = new Menu(new novoArquivo());
		
		menu.add(new Opcao("Voltar", "voltar"));
		menu.add(new Opcao("Abrir arquivo", "abrirArquivo"));
		menu.add(new Opcao("Arquivo em branco", "emBranco"));
		
		boolean retorno = (boolean) menu.executar();
		
		if (retorno == true) {
			System.out.println("Arquivo aberto no editor: " + Arquivos.size());
		}
		return retorno;
	}

	public boolean trocarArquivo() throws Exception {
		System.out.println("Qual editor você deseja abrir? (Disponivel: " + Arquivos.size() + ")");
		int resposta = scanner.nextInt();
		if (resposta > Arquivos.size() || resposta < 1) {
			throw new Exception("Editor não encontrado.");
		}
		Arquivos.setEditor(Arquivos.get(resposta));
		return true;
	}

	public boolean editarDocumento() throws Exception {
		System.out.println("Digite a linha que deseja editar: ");
		int linha = scanner.nextInt();
		if (linha < 1) {
			throw new Exception("Linha inválida.");
		}

		System.out.println("Digite a coluna que deseja editar: ");
		int coluna = scanner.nextInt();
		if (coluna > Editor.MAX_COLUNAS || coluna < 1) {
			throw new Exception("Coluna inválida.");
		}
		
		System.out.println("Digite o texto que deseja inserir na linha " + linha + ", coluna " + coluna + ": ");
		try {
			System.out.println("Texto atual: " + Arquivos.getEditor().getTexto(linha, coluna));
		} catch (Exception e) {}
		
		scanner.nextLine();
		String texto = scanner.nextLine();

		for (String palavra : texto.split(" ")) {
			Arquivos.getEditor().setTexto(linha, coluna, palavra);
			coluna++;
			if (coluna > Editor.MAX_COLUNAS) {
				linha++;
				coluna = 1;
			}
		}
		return true;
	}

	public boolean verDocumento() {
		Arquivos.getEditor().verDocumento();
		return true;
	}

	public boolean salvarDocumento() throws Exception {
		System.out.println("Qual arquivo será salvo?");
		String diretorio = scanner.nextLine();

		try {
			Arquivos.getEditor().salvarDocumento(diretorio);
			System.out.println("Arquivo salvo em: " + diretorio);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o arquivo:" + e.getCause());
		}
		return true;
	}
	
}
