package br.com.trabalho.dcc062.menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private static Scanner scanner = new Scanner(System.in);

	private List<Opcao> opcoes = new ArrayList<Opcao>();
	private Object classe;
	
	public Menu(Object classe) {
		this.classe = classe;
	}
	
	public void add(Opcao opcao) {
		opcoes.add(opcao);
	}
	
	public Object executar() {
		try {
			imprimirOpcoes();
			
			boolean correto = false;
			int resposta;
			do {
				resposta = scanner.nextInt();
				if (resposta >= opcoes.size() || resposta < 0) {
					System.out.println("Opção inválida, selecione:");
					imprimirOpcoes();
				} else {
					correto = true;
				}
			} while(correto == false);

			Class<?> classObj = classe.getClass();
			try {
				Method printMessage = classObj.getDeclaredMethod(opcoes.get(resposta).funcao);
				return printMessage.invoke(classe);
			} catch (NoSuchMethodException e) {
				System.out.println("Não foi encontrado a função para esta ação.");
				return true;
			} catch (InvocationTargetException e) {
				System.out.println("Não foi possível obter o retorno da ação.");
				return true;
			}

		} catch (Exception e) {
			System.out.println("Ocorreu um erro interno: " + e);
			return false;
		}
	}
	
	private void imprimirOpcoes() {
		for (int i = 0; i < opcoes.size(); i++) {
			System.out.println(i + " - " + opcoes.get(i).nome);
		}
	}

}
