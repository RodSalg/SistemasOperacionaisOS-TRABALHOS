import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ShellMain {
	
	
	//getParentFile - retorna todos os arquivos anteriores ao que eu to ex a/b/c, retorna a/b
	

    
    public static void main(String[] args) throws IOException {
        
    	System.out.println("Thiago Rodrigo Monteiro Salgado [versão 21.95.44.56]\r\n"
    			+ "(c) Professor Eduardo Luzeiro Feitosa. Todos os direitos reservados.\n");

    	
    	//**************************************************************************
    	//**************************************************************************
    	
    	FileWriter logExec = null;
    	try {
			 logExec = new FileWriter("logExec.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Algum problema ao criar o arquivo, examinar!");
			e.printStackTrace();
		}
    	
    	
    	Scanner scan =  new Scanner(System.in);
        String comandoUser = "";
        File caminho = new File(System.getProperty("user.home"));
        TerminalLinux termLinux = new TerminalLinux();
        boolean run = true;
       
        while(run) {
        	
            System.out.print(caminho.getPath() + ">");
            comandoUser = scan.nextLine();
            logExec.write(comandoUser);
            logExec.write("\n");
            String[] comandoUsuario = comandoUser.split(" "); 
            
            
            
            if(comandoUser.equals("exit"))
            {
            	run = false;
            	
            }else {
            	
            	caminho = termLinux.runTerminal(comandoUsuario, caminho);
                
            }
            

            

        }
        logExec.close();
        scan.close();
    }
    
}
