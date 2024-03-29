import java.io.File;

public class TerminalLinux extends Comandos {
	private String[] comandoUsuario;
	private File caminho;
	
	
//	public TerminalLinux(String[] comandoUsuario, File caminho)
//	{
//		
//		this.comandoUsuario = comandoUsuario;
//		this.caminho = caminho;
//		
//	}
	
	
	public File runTerminal(String[] comandoUsuarioParametro, File caminhoParametro)
	{
		
        this.comandoUsuario = comandoUsuarioParametro;
        this.caminho = caminhoParametro;
        
        if(comandoUsuario[0].equals("cd"))
        {
        	this.caminho = this.cd(comandoUsuario, caminho);
        	
        }else if(comandoUsuario[0].equals("pwd"))
        {
        	this.pwd(caminho);
        }else if(comandoUsuario[0].equals("ls"))
        {
        	this.ls(caminho);
        }else if(comandoUsuario[0].equals("cat"))
        {
        	this.cat(comandoUsuario, caminho);
        }else if(comandoUsuario[0].equals("cp"))
        {
        	this.cp(comandoUsuario, caminho);
        }else if(comandoUsuario[0].equals("mv"))
        {
        	this.mv(comandoUsuario, caminho);
        }else if(comandoUsuario[0].equals("mkdir"))
        {
        	this.mkdir(comandoUsuario, caminho);
        }else if(comandoUsuario[0].equals("clear"))
        {
        	this.clear();
        }
        else if(comandoUsuario[0].equals("rm"))
        {
        	this.rm(comandoUsuario, caminho);
        }
        else if(comandoUsuario[0].equals("mkdir"))
        {
        	this.cat(comandoUsuario, caminho);
        }else if(comandoUsuario[0].equals("head"))
        {
        	this.head(comandoUsuario, caminho);
        }else {
        	System.err.println("Comando inválido");
        }
        
        
        return caminho;
	
	}
		
	

}
