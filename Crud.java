import java.util.Scanner; 
import java.util.ArrayList;

//com disco 
import java.util.Collections;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;

public class Crud //Beatriz 
{
	public static String xop;
	public static Scanner teclado;
	public static ArrayList<Livros> listaLiga;

	public static String xId, xNome, xAutor, xEditora, xPreco;
	public static double preco; 
	
	public static void Cls(){
		for(int linha = 0; linha < 20; linha++);
		System.out.println(""); 
	}

	public static String Digitar(String txt)
	{
		System.out.print(txt);
		xop = teclado.nextLine().toUpperCase();
		return xop;
	}
	
	public static void mensagem(String txt)
	{
		System.out.print(txt);
		teclado.nextLine();
	}
		
	
	public static void main(String [] args)
	{
		teclado = new Scanner(System.in);
		listaLiga = new ArrayList<Livros>();
		Lertxt();
		while(true)
		{
			Cls();
			System.out.println("*************************");
			System.out.println("Menu de Opcoes - Livros");
			System.out.println("*************************");
			System.out.println("qtde= " + listaLiga.size());
			System.out.println("<1> Incluir livros............");
			System.out.println("<2> Alterar livros............");
			System.out.println("<3> Excluir livros............");
			System.out.println("<4> Listar livros.............");
			System.out.println("<5> Gravar arquivo.txt........");
			System.out.println("<6> Ler Livros.txt............");
			System.out.println("<7> Classificar Livros...");
			System.out.println("<8> Procurar um Livro.........");
			System.out.println("<9> Fim opcoes...............");
			xop = Digitar("\n\nQual opcao(1-9): ");
			
			try{
				int op=Integer.parseInt(xop);
				if(op<1 || op>9){
					mensagem("Valor invalido! Tecle <enter>");
				}
				if(op==1)Incluir();
				if(op==2)Alterar();
				if(op==3)Excluir();
				if(op==4)Listar();
				if(op==5)Gravartxt();
				if(op==6)Lertxt();
				if(op==7)opClass();
				if(op==8)Procurar();
				if(op==9)System.exit(0);
		
			}catch(Exception erro){
				mensagem("Valor invalido! Tecle <enter>");
				Cls();
			}
			
		}//while
			
	}//main

	public static int CriaID()
	{
		if(listaLiga.isEmpty())
			return 1;
		int tam = listaLiga.size();
		int ult = tam-1;
		Livros aux = new Livros();
		aux = (Livros) listaLiga.get(ult);
		return aux.getId()+1;
	}
	
	public static void Listar()
	{
		if (listaLiga.isEmpty()){
			mensagem("Lista vazia");
			return;
		}
		for (int i=0; i<listaLiga.size(); i++){
			Livros aux = new Livros();
			aux = (Livros) listaLiga.get(i);
			System.out.println("Livro: " + i);
			aux.ToString();
			System.out.println("--------------------");
		}
		mensagem("Tecle <enter>");
	}

	public static void listaemLinha(){
		Cls();
		if (listaLiga.isEmpty()){
			mensagem("Lista vazia"); return;
		} 
		System.out.println(("ID\t Nome\t Autor\t Editora\t Preco"));
		for (int i=0; i<listaLiga.size(); i++){
			Livros aux = new Livros();
			aux = (Livros) listaLiga.get(i);
			aux.emLinha();
			System.out.println("\n");
		}
	}
	
	public static void Incluir()
	{
		Cls();
		System.out.println("*****INCLUIR LIVROS*****\n\n");
		System.out.println("Novo Registro: ID = "+CriaID());

		xNome = Digitar("Nome: ");
		xAutor = Digitar("Autor: ");
		xEditora = Digitar ("Editora: ");
		xPreco = Digitar ("Preco: ");

		System.out.println("*****Dados Digitados*****\n\n");
		System.out.print("ID: "+CriaID());
		System.out.println("Nome: "+ xNome);
		System.out.println("Autor: "+ xAutor);
		System.out.println("Editora: "+ xEditora);
		System.out.println("Preco: "+ xPreco);

		xop = Digitar("\n\nConfirmar dados digitados (s/n): ");
		if (xop.equals("s") || xop.equals("S")){
			Livros aux = new Livros();
			aux.setId(CriaID());

			aux.setNome(xNome);
			aux.setAutor(xAutor);
			aux.setEditora(xEditora);

			try{
				preco = Double.parseDouble(xPreco);
			}catch(Exception errpreco){
				mensagem("Preço inválido!!!"); preco = 1.00;
			}

			aux.setPreco(preco); 
			listaLiga.add(aux);
			Gravartxt();
			mensagem("Cadastrado com sucesso!!!");
		}else{
			mensagem("Valores não encontrados");
		}
	}
	
	public static void Alterar()
	{
		Cls();
		System.out.println("*****Alterar Livros *****");

		if(listaLiga.size()==0){
			mensagem("Lista Vazia");
			return;
		}

		listaemLinha();
		int ultimo = listaLiga.size()-1;
		xop = Digitar("\n\nQual Registro Alterar (0-"+ ultimo+")?");
		
		try{
			int reg = Integer.parseInt(xop);
			if (reg>=0 && reg<=ultimo){
				AlterandoDados(reg);
			}else {
				throw new Exception();
			}
		}catch(Exception erro_exclu){
			mensagem("Registro Inválido "+ xop);
		}
	}

	public static void AlterandoDados(int pos){
		Livros aux = (Livros) listaLiga.get(pos);
		while(true){
			Cls();
			System.out.println(("*****Alterando Dados dos Livros"));
			System.out.println("Registro: " + pos);
			System.out.println("ID: " + aux.getId());
			System.out.println("[1]Nome: " + aux.getNome());
			System.out.println("[2]Autor: " + aux.getAutor());
			System.out.println("[3]Editora: " + aux.getEditora());
			System.out.println("[4]Preco: " + aux.getPreco());
			System.out.println("[5]Fim das alteracoes");
			xop = Digitar("Qual campo vc quer alterar(1-5)?");
			if(xop.equals("5")){
				Gravartxt();
				break;
			}
			if(xop.equals("1")){
				aux.setNome(Digitar("Digite um novo Nome: ")); 
				continue;
			}
			
			if(xop.equals("2")){
				aux.setAutor(Digitar("Digite um novo autor: ")); 
				continue;
			}
			
			if(xop.equals("3")){
				aux.setEditora(Digitar("Digite outra editora: ")); 
				continue;
			}
			
			if(xop.equals("4")){
				xPreco=Digitar("Digite um novo Preco: ");
				try
				{
					preco=Double.parseDouble(xPreco);
				}
				catch(Exception errpreco)
				{
					mensagem("Valor invalido!!<enter>");
				}
				aux.setPreco(preco);
			}
			mensagem(("Opcao invalida!"+xop));
		}
	}
	
	public static void Excluir()
	{
		Cls();
		System.out.println("*****Excluir Livros *****");
		listaemLinha();

		if(listaLiga.size()==0){
			mensagem("Lista Vazia");
			return;
		}

		int ultimo = listaLiga.size()-1;
		xop = Digitar("Qual Registro Excluir (0-"+ ultimo+")?");
		try{
			int reg = Integer.parseInt(xop);
			if (reg>=0 && reg<=ultimo){
				listaLiga.remove(reg);
				mensagem("Excluido com sucesso - Registro " +reg);
			}else {
				mensagem("Não excluiu nada! Registro invalido: "+reg);
			}
		}catch(Exception erro_exclu){
			mensagem("Registro Inválido ");
		}
	}
	
	public static void Gravartxt(){
		try {
			if (listaLiga.isEmpty()){
				mensagem("Lista vazia"); 
				return;
			}

			Formatter arquivo = new Formatter("Livros.txt");
			Livros aux = new Livros();
			for(int i=1; i<=listaLiga.size();i++)
			{
				aux = (Livros) listaLiga.get(i);
				xId = ""+aux.getId();
				xNome = aux.getNome();
				xAutor = aux.getAutor();
				xEditora = aux.getEditora();
				xPreco = ""+aux.getPreco();
				arquivo.format("%s,%s,%s,%s,%s\n", xId, xNome, xAutor, xEditora, xPreco);
				mensagem("Gravando Livro: "+i);
			}//for
			arquivo.close();
		} catch (FileNotFoundException e) {
			mensagem("Erro gravacao="+e);
		}
	}
	
	public static void Lertxt(){
		try {
			File arquivo = new File("Livros.txt");
			if(!arquivo.exists())
			{
				System.out.println("Arquivo nao existe!!!!!");
				return;
			}
			Scanner sc = new Scanner(arquivo);
			sc.useDelimiter("\\s*,\\s*");
			if(listaLiga.size()>0)
				listaLiga.clear();
			while(sc.hasNext()){
				xId = sc.next();
				xNome = sc.next();
				xAutor = sc.next();
				xEditora = sc.next();
				xPreco = sc.next();
				Livros aux = new Livros();
				aux.setId(Integer.parseInt(xId));
				aux.setNome(xNome); 
				aux.setAutor(xAutor);
				aux.setEditora(xEditora);
				aux.setPreco(Double.parseDouble(xPreco));
				listaLiga.add(aux);
				System.out.println("Lendo = " + xId+xNome+xAutor+xEditora+xPreco);
				teclado.nextLine();
			}///while
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro leitura="+e);
		}
	}

	public static void Classifica(){
		Cls();
		listaemLinha();
		Collections.sort(listaLiga);
		listaemLinha();
		Lertxt();
	}
	public static void Procurar(){
		Cls();
		if(listaLiga.isEmpty()){
			mensagem("Lista vazia!");
			return;
		}
		System.out.println("Procurar dados - Livros");
		xop = Digitar("Digitar o valor que vc procura: ");
		int flag=0;
		System.out.println("******* Listando Livros ********");
		System.out.println("Registro, ID, Nome, Autor, Editora, Preco");
		Livros aux = new Livros();
		for (int i=0; i<listaLiga.size(); i++){
			aux = (Livros)listaLiga.get(i);
			xNome=aux.getNome();
			xAutor=aux.getAutor();
			xEditora=aux.getEditora();
			xPreco=""+aux.getPreco();
			flag=0;
			if(xNome.contains(xop))flag=1;
			if(xAutor.contains(xop))flag=1;
			if(xEditora.contains(xop))flag=1;
			if(xPreco.contains(xop))flag=1;
			if(flag==1){
				System.out.print(""+i);
				System.out.print(",\t"+aux.getNome());
				System.out.print(",\t"+aux.getAutor());
				System.out.print(",\t"+aux.getEditora());
				System.out.print(",\t"+aux.getPreco());
			}
		}
		Digitar("\nTecle <enter>");
	}

	public static void opClass(){
		Cls();
		System.out.print("******Opcao para classificar: *****\n");
		System.out.println("1- Por Autor ");
		System.out.println("2 - Por Editora ");
		xop = Digitar("Qual a opcao (1-2)? ");

		if(xop.equals("1"))
			Collections.sort(listaLiga, new autorCompara());
		if(xop.equals("2"))
			Collections.sort(listaLiga, new editoraCompara());
		listaemLinha();
		Lertxt();
	}

}//fim class