package br.com.trabalho.dcc062;

import br.com.trabalho.dcc062.editor.Arquivos;
import br.com.trabalho.dcc062.menu.Menu;
import br.com.trabalho.dcc062.menu.Opcao;
import br.com.trabalho.dcc062.menu.acoes.Inicio;

public class Principal {

	public static void main(String[] args) {
		new Arquivos();
		Menu menu = new Menu(new Inicio());
		
		menu.add(new Opcao("Sair", "sair"));
		menu.add(new Opcao("Abrir arquivo", "abrirArquivo"));
		menu.add(new Opcao("Trocar de arquivo", "trocarArquivo"));
		menu.add(new Opcao("Editar o documento", "editarDocumento"));
		menu.add(new Opcao("Ver o documento.", "verDocumento"));
		menu.add(new Opcao("Salvar", "salvarDocumento"));

		boolean loop = true;

		do {
			loop = (boolean) menu.executar();
		} while (loop == true);
		
		System.out.println("Finalizado!");
	}
}
