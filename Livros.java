//Livros.java
public class Livros implements Comparable<Livros>
{
   private int id_livro;
   private String nomeLivro, autor, editora;
   private double preco;

   public int compareTo(Livros outro){
    return this.getAutor().compareTo(outro.getAutor());
   }

   Livros(){
       setId(0);
       setNome("");
       setAutor("");
       setEditora("");
       setPreco(0.0);
   }

   public void setId(int i){
	   this.id_livro = i;
   }

   public void setNome(String n){
	   this.nomeLivro = n;
   }

   public void setAutor(String a){
		this.autor = a;
   }

   public void setEditora(String e){
		this.editora = e;
   }

   public void setPreco(double p){
		this.preco = p;
   }

   public int getId(){
	   return this.id_livro;
   }

    public String getNome(){
		return this.nomeLivro;
   }

   public String getAutor(){
	   return this.autor;
   }

   public String getEditora(){
	   return this.editora;
   }
   
   public double getPreco(){
	   return this.preco;
   }

   public void ToString(){
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Autor: " + getAutor());
        System.out.println("Editora: " + getEditora());
        System.out.println("Preco: " + getPreco());
   }
   public void emLinha(){
    System.out.print("" + getId());
    System.out.print(", \t" + getNome());
    System.out.print(", \t" + getAutor());
    System.out.print(", \t" + getEditora());
    System.out.print(", \t" + getPreco());
}
}
