
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class RunnableClassificacao implements Runnable {

    public ListView listViewClassificacao;
    public RunnableCavalo[] runnablesCavalos;
    public ObservableList<String> observableListClassificacao;
    public List<String> listClassificacao = new ArrayList();

    public RunnableClassificacao(ListView v1, RunnableCavalo[] v2) {
        listViewClassificacao = v1;
        runnablesCavalos = v2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

        //Apenas quando os cinco cavalos tiverem ultrapassado a linha de chegada será exibida a classificação
        while (RunnableCavalo.qtdCavalosChegaram < 5) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }

        //Ordenando os runnableCavalos (Runnables) no vetor por ordem de chegada
        RunnableCavalo aux;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (runnablesCavalos[i].posicao < runnablesCavalos[j].posicao) {
                    aux = runnablesCavalos[i];
                    runnablesCavalos[i] = runnablesCavalos[j];
                    runnablesCavalos[j] = aux;
                }
            }
        }
        
        for (int i = 0; i < 5; i++) {
            listClassificacao.add(runnablesCavalos[i].nome);
        }
        
        observableListClassificacao = FXCollections.observableArrayList(listClassificacao);
        listViewClassificacao.setItems(observableListClassificacao);

    }

}
