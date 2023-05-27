import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Comandos {
	
	private File til;
	
	public Comandos()
	{
		this.setTil(new File(System.getProperty("user.home")));
	}
	
	public File cd(String[] comandoUsuario, File caminho )
	{
        if(comandoUsuario.length > 1) {
        	
        	
            String comandoAux = comandoUsuario[1]; 
            //salvando a 2nda parte, ex: cd videos
            // comandoUsuario[0] = cd, comandoUsuario[0] = videos
            //dessa forma consigo pegar para qual pasta o usuario deseja ir
            if(comandoAux.equals("..")) {
            	caminho = caminho.getParentFile();
            }else if(comandoAux.equals("~")) {
            	
            	 File camAux = til;
            	
                if(camAux.exists() && camAux.isDirectory()) { //verifica se existe e se é de fato um diretório
                	
                	caminho = camAux;
                	
                } else {
                    System.out.println("Diretorio nao encontrado: " + comandoAux);
                }
            	
            } else {
                File camAux = new File(caminho, comandoAux); //cam aux - guardando novo diretorio
                
                if(camAux.exists() && camAux.isDirectory()) { //verifica se existe e se é de fato um diretório
                	
                	caminho = camAux;
                	
                } else {
                    System.out.println("Diretorio nao encontrado: " + comandoAux);
                }
                
            }
        }
        
        return caminho;
	}
	
	
	public void pwd(File caminho)
	{
		System.out.println(caminho.getPath());
	}
	
	public  void clear() {
	    for(int i = 0; i < 60; i++)
	    {
	    	System.out.println();
	    }
	}
	
	public void ls(File caminho)
	{
        for(File arquivoCursor : caminho.listFiles()) {
        	
            System.out.println(arquivoCursor.getName());
        
        }
	}
	
	public void cat(String[] comandoUsuario, File caminho)
	{
        if(comandoUsuario.length > 1) {
            Path caminhoArquivo = Paths.get(caminho.getPath(), comandoUsuario[1]);
            if(Files.exists(caminhoArquivo) && !Files.isDirectory(caminhoArquivo))
            {
            	
                try {
					Files.lines(caminhoArquivo).forEach(System.out::println);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            } else {
            	
                System.out.println("Arquivo nao encontrado ou é um diretorio: " + comandoUsuario[1]);
            }
        }
        
        
        
	}
	
	public void cp(String[] comandoUsuario, File caminho)
	{
        if(comandoUsuario.length > 2) {
            Path caminhoAtual = Paths.get(caminho.getPath(), comandoUsuario[1]);
            Path caminhoDestino = null;
            
            String pontoBarra = comandoUsuario[2];
            String[] pontoBarraQuebrado = pontoBarra.split("/");
            
            if(pontoBarraQuebrado[0].equals("."))
            {
            	caminhoDestino = Paths.get( caminho.getPath(),comandoUsuario[2]);
            }else {
            	caminhoDestino = Paths.get( comandoUsuario[2]);
            }

            	  	
            if(Files.exists(caminhoAtual) && !Files.isDirectory(caminhoAtual)) {
                try {
					Files.copy(caminhoAtual, caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("Arquivo nao encontrado ou é um diretorio: " + comandoUsuario[1]);
            }
        }
	}
	
	public void mv(String[] comandoUsuario, File caminho) 
	{
        if(comandoUsuario.length > 2) {
            Path caminhoAtual = Paths.get(caminho.getPath(), comandoUsuario[1]);
            Path caminhoDestino = null;
            
            
            String pontoBarra = comandoUsuario[2];
            String[] pontoBarraQuebrado = pontoBarra.split("/");
            
            if(pontoBarraQuebrado[0].equals("."))
            {
            	caminhoDestino = Paths.get( caminho.getPath(),comandoUsuario[2]);
            }else {
            	caminhoDestino = Paths.get( comandoUsuario[2]);
            }
            
            
            if(Files.exists(caminhoAtual) && !Files.isDirectory(caminhoAtual)) {
                try {
                	 if( !Files.isDirectory(caminhoDestino) )
                	 {
                		 Files.move(caminhoAtual, caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
                	 }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("O arquivo " + comandoUsuario[1] + " não foi encontrado ou é uma pasta: " );
            }
        
        }
        
	
	}
	
	public void mkdir(String[] comandoUsuario, File caminho)
	{
        if(comandoUsuario.length > 1) {
            Path diretorio = Paths.get(caminho.getPath(), comandoUsuario[1]);
            if(!Files.exists(diretorio)) {
                try {
					Files.createDirectory(diretorio);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("Esta pasta já existe. Por favor crie com um outro nome!");
            }
        }
		
	}
	
	public void rm(String[] comandoUsuario, File caminho)
	{
		
        if(comandoUsuario.length > 1) {
            Path caminhoArquivo = Paths.get(caminho.getPath(), comandoUsuario[1]);
            if(Files.exists(caminhoArquivo) && !Files.isDirectory(caminhoArquivo)) {
                try {
					Files.delete(caminhoArquivo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
            	 System.out.println("O arquivo " + comandoUsuario[1] + " não foi encontrado ou é uma pasta: " );
            }
        }
		
	}
	
	public void head(String[] comandoUsuario, File caminho)
	{
		int cabecalho = 10; //posso mudar
        if(comandoUsuario.length > 1) {
            Path caminhoArquivo = Paths.get(caminho.getPath(), comandoUsuario[1]);
            if(Files.exists(caminhoArquivo) && !Files.isDirectory(caminhoArquivo)) {
                try {
					Files.lines(caminhoArquivo).limit(cabecalho).forEach(System.out::println);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
            	 System.out.println("O arquivo " + comandoUsuario[1] + " não foi encontrado ou é uma pasta: " );
            }
        }
		
	}

	public File getTil() {
		return til;
	}

	public void setTil(File til) {
		this.til = til;
	}
	
	
	
	
	
	

}
