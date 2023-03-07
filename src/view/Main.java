/*
RESUMO      : Página principal com os comandos solicitados no exercício 01
PROGRAMADORA: Luiza Felix
DATA        : 02/03/2023
 */

package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController redes = new RedesController();
		int opcao = 0;

		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma configuração para ser acessada:\n 2) Redes IPv4\n 3) PING Médio\n Para SAIR digite 0."));
			switch (opcao) {
			case 2:
				redes.ip();
				break;
			case 3:
				redes.ping();
				break;

			case 0:
				System.out.println("Programa finalizado com sucesso! ");
				break;
			default:
				System.out.println("!!! ERRO: Digite uma opção válida!!!");
			}

		} while (opcao != 0);

	}

}
