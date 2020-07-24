/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author java
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button buttonIniciar;
    @FXML
    private Button buttonCavalo1;
    @FXML
    private Button buttonCavalo2;
    @FXML
    private Button buttonCavalo3;
    @FXML
    private Button buttonCavalo4;
    @FXML
    private Button buttonCavalo5;
    @FXML
    private Label labelLinhaChegada;
    @FXML
    private ListView listViewClassificacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    public void handleButtonIniciar() {
        //Instanciando as Runnables
        RunnableCavalo[] runnablesCavalos = new RunnableCavalo[5];
        runnablesCavalos[0] = new RunnableCavalo(buttonCavalo1, "Cavalo 1");
        runnablesCavalos[1] = new RunnableCavalo(buttonCavalo2, "Cavalo 2");
        runnablesCavalos[2] = new RunnableCavalo(buttonCavalo3, "Cavalo 3");
        runnablesCavalos[3] = new RunnableCavalo(buttonCavalo4, "Cavalo 4");
        runnablesCavalos[4] = new RunnableCavalo(buttonCavalo5, "Cavalo 5");
        
        //Alterando as prioridades de algumas Threads (apenas para observar o resultado)
        runnablesCavalos[0].prioridade = 10;
        runnablesCavalos[1].prioridade = 7;

        //Instanciando as Threads
        Thread[] threadsCavalos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threadsCavalos[i] = new Thread(runnablesCavalos[i], "Cavalo " + (i + 1));
            threadsCavalos[i].start();
        }
        
        //Instanciando a Thread para a Classificação (ListView)
        RunnableClassificacao runnableClassificacao = new RunnableClassificacao(listViewClassificacao, runnablesCavalos);
        Thread threadClassificacao = new Thread(runnableClassificacao, "Classificação");
        threadClassificacao.start();
    }


}
