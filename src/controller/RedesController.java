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
			lerip("ifconfig");

		}

	}

	private void lerip(String processo) {
		// esse vai fazer a leitura do IP

		try {
			Process ip = Runtime.getRuntime().exec(processo);

			InputStream fluxo = ip.getInputStream();// lê as linhas do IP em bits
			InputStreamReader leitor = new InputStreamReader(fluxo); // converte o fluxo de bits para algo legível
			BufferedReader buffer = new BufferedReader(leitor); // vai ler as linhas de bit traduzidas com as
																// informações fornecidas pelo processo filho

			String linha = buffer.readLine();
			 while (linha != null) {
				if (linha.contains("Adaptador")) {
//					a criação do buffer depende da linha conter a variável indicada.
					StringBuffer adaptador = new StringBuffer();
					adaptador.append(linha); // salvo o nome do adaptador para mostrar
					linha = buffer.readLine();

//					aqui estou lendo as linhas e verificando se não mudei de adaptador, se o adaptador tiver "IPv4", o código mostra ele
					while (linha.contains("Adaptador") == false) {
						if (linha.contains("IPv4")) {
							adaptador.append("\n" + linha);
							System.out.println(adaptador);
						}
						linha = buffer.readLine();
					}
				}else { // sempre vai aparecer a mensagem de erro pq a linha dá "null" na última "rodada"
				linha = buffer.readLine();
				}

			}
			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	3)
	public void ping() {

		String SO = os();
		if (SO.contains("Windows")) {
			lerping("PING -4 -n 10 www.google.com.br");
		} else { // é Linux
			lerping(": PING -4 -c 10 www.google.com.br");
		}

	}

	private void lerping(String processo) {
		
		try {
			Process ping = Runtime.getRuntime().exec(processo);

//		transformação no fluxo de bits em algo legível
			InputStream fluxo = ping.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();

			while (linha != null) {
				if (linha.contains(" M�dia = ")) {
					String[] palavra = linha.split("M�dia = ");
					System.out.println("PING Médio: "+palavra[1]);
					}
				linha = buffer.readLine();	
			} 
			buffer.close();
			leitor.close();
			fluxo.close();
	
		}catch(IOException e){
		e.printStackTrace();
		}
	}
}
