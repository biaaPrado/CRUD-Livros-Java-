import java.util.Comparator;

public class editoraCompara implements Comparator<Livros> {
    public int compare(Livros l1, Livros l2){
        return l1.getEditora().compareTo(l2.getEditora());
    }
} 