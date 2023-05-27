import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * Esta classe e totalmente dedicada a leitura do arquivo texto.
 * Aqui o arquivo texto sera lido e sera devolvido como uma string
 * de forma que os outros métodos possma interpreta-lo com maior 
 * facilidade.
 * */
public class LeituraArquivo
{
	private String arquivoTexto;

	/**
	 * @param nomeArquivo - como paramentro daremos o nome do arquivo texto que desejamos executar
	 * @return linha;
	 * */
	public LeituraArquivo(String nomeArquivo)
	{

		try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
			Scanner input = new Scanner(System.in);
			
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
            	System.out.println(line);
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            arquivoTexto = sb.toString();
            input.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
		
		
		
		
	}
	
	/**
	 * a funcao pega o arquivo todo em forma de uma string
	 * @return arquivoTexto;
	 * */
    public String getTextoCompleto() {
        return this.arquivoTexto;
    }
	

}