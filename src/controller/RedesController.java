/*
RESUMO      : Página RedesController com os comandos solicitados no exercicio 01
PROGRAMADORA: Luiza Felix
DATA        : 02/03/2023
 */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

//	1)
	private String os() {

		String so = System.getProperty("os.name");
//		String aq = System.getProperty("os.arch");
//		String vr = System.getProperty("os.version");

		return so;
	}

//	2)
	public void ip() {

		String SO = os();
		if (SO.contains("Windows")) {
			lerip("ipconfig");
		} else { // é Linux

		}

	}

	private void lerip(String processo) {
		//esse vai fazer a leitura do IP
		
		try {
			Process ip = Runtime.getRuntime().exec(processo);

			InputStream fluxo = ip.getInputStream();//lê as linhas do IP em bits
			InputStreamReader leitor = new InputStreamReader(fluxo); // converte o fluxo de bits para algo legível
			BufferedReader buffer = new BufferedReader(leitor); //vai ler as linhas de bit traduzidas com as informações fornecidas pelo processo filho

			String linha = buffer.readLine();
			while (linha != null) {
				if(linha.contains("Adaptador")) {
					
					StringBuffer adaptador = new StringBuffer();
					adaptador.append(linha); //salvo o nome do adaptador para mostrar
				
					linha = buffer.readLine();

//					aqui estou lendo as linhas e colocando tudo no buffer, para depois analisá-las
					while(linha.contains("Adaptador")== false) {
						adaptador.append("\n"+linha);
						linha = buffer.readLine();	
					}
					
					String adapt = adaptador.toString();
					if (adapt.contains("IPv4")) {
						System.out.println(adaptador);
					}
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
