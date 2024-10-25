import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static int retirarMenor(ArrayList<Integer> lista) {
        int menor = lista.getFirst();
        int indice = 0;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) < menor) {
                menor = lista.get(i);
                indice = i;
            }
        }
        return indice;
    }

    public static int[] arrayOrdemCrescente(ArrayList<Integer> lista) {
        int[] novoArray = new int[lista.size()];

        int tamanhoArray = lista.size();

        for (int i = 0; i < tamanhoArray; i++) {
            int indice = retirarMenor(lista);
            novoArray[i] = lista.get(indice);
            lista.remove(indice);
        }

        return novoArray;
    }

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<Integer>();

        lista.add(1);
        lista.add(10);
        lista.add(5);
        lista.add(6);
        lista.add(11);
        lista.add(7);
        lista.add(4);
        System.out.println(Arrays.toString(arrayOrdemCrescente(lista)));
    }
}
