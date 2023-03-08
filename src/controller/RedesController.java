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
			ip_Windows("ipconfig");
		} else { // é Linux
			ip_Linux("ifconfig");

		}

	}

	private void ip_Windows(String processo) {
		// esse vai fazer a leitura do IP

		try {
			Process ip = Runtime.getRuntime().exec(processo);

			InputStream fluxo = ip.getInputStream();// lê as linhas do IP em bits
			InputStreamReader leitor = new InputStreamReader(fluxo); // converte o fluxo de bits para algo legível
			BufferedReader buffer = new BufferedReader(leitor); // vai ler as linhas de bit traduzidas com as informações fornecidas pelo processo filho

			String linha = buffer.readLine();
			StringBuffer adaptador = new StringBuffer(); //cria o buffer bonitinho sem que ele seja "recriado" a cada rodada
			
			 while (linha != null) {
				if (linha.contains("Adaptador")) {				
					adaptador.delete(0, adaptador.length());
					adaptador.append(linha); // salvo o nome do adaptador para mostrar
				}else { // sempre vai aparecer a mensagem de erro pq a linha dá "null" na última "rodada"
					if (linha.contains("IPv4")) {
						adaptador.append("\n" + linha);
						System.out.println(adaptador);
					}
				}
				linha = buffer.readLine();

			}
			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void ip_Linux(String processo) {
		// esse vai fazer a leitura do IP

				try {
					Process ip = Runtime.getRuntime().exec(processo);

					InputStream fluxo = ip.getInputStream();// lê as linhas do IP em bits
					InputStreamReader leitor = new InputStreamReader(fluxo); // converte o fluxo de bits para algo legível
					BufferedReader buffer = new BufferedReader(leitor); // vai ler as linhas de bit traduzidas com as
																		// informações fornecidas pelo processo filho

					String linha = buffer.readLine();
					StringBuffer adaptador = new StringBuffer(); //cria o buffer bonitinho sem que ele seja "recriado" a cada rodada
					
					 while (linha != null) {
						if (linha.contains("flags")) {				
							adaptador.delete(0, adaptador.length());
							adaptador.append(linha); // salvo o nome do adaptador para mostrar
						}else { // sempre vai aparecer a mensagem de erro pq a linha dá "null" na última "rodada"
							if (linha.contains("inet ")) {
								adaptador.append("\n" + linha);
								System.out.println(adaptador);
							}
						}
						linha = buffer.readLine();

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
			
			if(processo == "PING -4 -n 10 www.google.com.br") {
				while (linha != null) {
					if (linha.contains(" M�dia = ")) {
						String[] palavra = linha.split("M�dia = ");
						System.out.println("PING Médio: " + palavra[1]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} else {
				while (linha != null) {
					if (linha.contains("rtt")) {
						String[] palavra = linha.split("/");
						System.out.println("PING Médio: " + palavra[6]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
