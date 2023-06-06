import java.util.Comparator;

public class autorCompara implements Comparator<Livros> {
    public int compare(Livros l1, Livros l2){
        return l1.getAutor().compareTo(l2.getAutor());
    }
}
