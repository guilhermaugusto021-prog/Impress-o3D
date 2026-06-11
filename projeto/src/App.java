import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // Dropdown box com as impressoras
        Impressora3D ender3 = new Impressora3D("Ender 3", 1500, 350, "ender3.jpg",
                "Impressora FDM, ideal para iniciantes e entusiastas");
        Impressora3D k1 = new Impressora3D("Creality K1", 3500, 500, "k1.jpg",
                "Impressora 3D de alta velocidade, ideal para profissionais e iniciantes");
        Impressora3D bambu = new Impressora3D("Bambu Lab A1", 4200, 400, "bambu_a1.jpg",
                "Impressora FDM, ideal para profissionais");

        ComboBox<Impressora3D> comboImpressora = new ComboBox<>();
        comboImpressora.getItems().addAll(ender3, k1, bambu);

        ImageView imagemImpressora = new ImageView();
        Label labelPreco = new Label();
        Label labelPotencia = new Label();
        Label labelDescricao = new Label();

        comboImpressora.setOnAction(e -> {
            Impressora3D selecionada = comboImpressora.getValue();
            labelPreco.setText("Preço: R$ " + selecionada.getPreco());
            labelPotencia.setText("Potência: " + selecionada.getPotenciaWatts() + " W");
            labelDescricao.setText(selecionada.getDescricao());
            Image imagem = new Image("file:src/resources/images/" + selecionada.getImagemPath());
            imagemImpressora.setFitWidth(200);
            imagemImpressora.setFitHeight(200);
            imagemImpressora.setPreserveRatio(true);
            imagemImpressora.setImage(imagem);
        });

        VBox infoBox = new VBox(12, labelPreco, labelPotencia, labelDescricao);
        infoBox.getStyleClass().add("info-impressora");

        VBox secao1 = new VBox(15, comboImpressora, imagemImpressora, infoBox);
        secao1.setPadding(new Insets(0, 0, 20, 0));

        // Seção 2 - Dados do Projeto
        Label labelArquivo = new Label("Arquivo do modelo:");
        TextField txtArquivo = new TextField();

        Label labelDescricaoProjeto = new Label("Descrição do objeto:");
        TextArea txtDescricao = new TextArea();

        Label labelPeso = new Label("Peso do modelo (g):");
        TextField txtPeso = new TextField();

        Label labelSuporte = new Label("Peso de suporte / desperdício (g):");
        TextField txtSuporte = new TextField();

        Label labelMaterial = new Label("Material:");
        ComboBox<String> comboMaterial = new ComboBox<>();
        comboMaterial.getItems().addAll("PLA");

        Label labelDensidade = new Label("Densidade / Qualidade:");
        ComboBox<MaterialImpressao> comboDensidade = new ComboBox<>();

        MaterialImpressao plaLow = new MaterialImpressao("Baixa (0,08 R$/g)", 0.08);
        MaterialImpressao plaMed = new MaterialImpressao("Média (0,12 R$/g)", 0.12);
        MaterialImpressao plaHigh = new MaterialImpressao("Alta (0,18 R$/g)", 0.18);
        comboDensidade.getItems().addAll(plaLow, plaMed, plaHigh);

        Label labelTempo = new Label("Tempo de impressão (estimado):");
        TextField txtTempo = new TextField();

        Label labelPreparo = new Label("Tempo de preparo / acabamento:");
        TextField txtPreparo = new TextField();

        Label labelTaxaFalha = new Label("Taxa de falha (%):");
        TextField txtTaxaFalha = new TextField();

        Label labelQuantidadePecas = new Label("Quantidade de peças:");
        TextField txtQuantidadePecas = new TextField();

        Label labelTarifa = new Label("Tarifa de energia:");
        RadioButton rbDiurna = new RadioButton("Diurna (R$ 0,95 / kWh)");
        RadioButton rbNoturna = new RadioButton("Noturna (R$ 0,65 / kWh)");
        ToggleGroup toggleTarifa = new ToggleGroup();
        rbDiurna.setToggleGroup(toggleTarifa);
        rbNoturna.setToggleGroup(toggleTarifa);
        rbDiurna.setSelected(true);

        Label labelMaoObra = new Label("Valor da mão de obra:");
        TextField txtMaoObra = new TextField();

        Label labelMargem = new Label("Margem de lucro desejada:");
        TextField txtMargem = new TextField();

        Button btnCalcular = new Button("Calcular Custos");

        GridPane secao2 = new GridPane();
        secao2.getStyleClass().add("formulario");

        secao2.setPadding(new Insets(30));
        secao2.setVgap(18);
        secao2.setHgap(20);

        secao2.add(labelArquivo, 0, 0);
        secao2.add(txtArquivo, 0, 1, 3, 1);
        secao2.add(labelDescricaoProjeto, 0, 2);
        secao2.add(txtDescricao, 0, 3, 3, 1);

        secao2.add(labelMaterial, 0, 4);
        secao2.add(labelDensidade, 1, 4);
        secao2.add(labelPeso, 2, 4);
        secao2.add(comboMaterial, 0, 5);
        secao2.add(comboDensidade, 1, 5);
        secao2.add(txtPeso, 2, 5);

        secao2.add(labelSuporte, 0, 6);
        secao2.add(labelTaxaFalha, 1, 6);
        secao2.add(labelQuantidadePecas, 2, 6);

        secao2.add(txtSuporte, 0, 7);
        secao2.add(txtTaxaFalha, 1, 7);
        secao2.add(txtQuantidadePecas, 2, 7);

        secao2.add(labelTempo, 0, 8);
        secao2.add(labelPreparo, 1, 8);

        secao2.add(txtTempo, 0, 9);
        secao2.add(txtPreparo, 1, 9);

        secao2.add(labelTarifa, 0, 10);
        secao2.add(labelMaoObra, 1, 10);

        secao2.add(rbDiurna, 0, 11);
        secao2.add(rbNoturna, 0, 12);
        secao2.add(txtMaoObra, 1, 11);

        secao2.add(labelMargem, 0, 13);
        secao2.add(txtMargem, 0, 14);

        secao2.add(btnCalcular, 0, 15, 2, 1);

        // =====================================================================
        // PAINEL DE RESULTADOS (DIREITO) - CORREÇÃO BLINDADA ANTI-CORTES (...)
        // =====================================================================
        GridPane direito = new GridPane();
        direito.setHgap(15);
        direito.setVgap(20);
        direito.setPadding(new Insets(30));
        direito.getStyleClass().add("painel-resultado");

        // Configuração de Restrições de Colunas robustas
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPrefWidth(220);
        col0.setHgrow(Priority.ALWAYS); // Força a expansão se houver texto longo

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(260);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(120);
        col2.setHgrow(Priority.NEVER); // Os valores finais ficam fixos na direita

        direito.getColumnConstraints().addAll(col0, col1, col2);

        // Cabeçalhos
        Label cabItem = new Label("Item");
        Label cabDetalhe = new Label("Detalhamento");
        Label cabCusto = new Label("Custo (R$)");
        cabItem.getStyleClass().add("cabecalho");
        cabDetalhe.getStyleClass().add("cabecalho");
        cabCusto.getStyleClass().add("cabecalho");

        direito.add(cabItem, 0, 0);
        direito.add(cabDetalhe, 1, 0);
        direito.add(cabCusto, 2, 0);

        // Nomes das Linhas (Coluna 0)
        Label lblNomeMaterial = new Label("Material (com falha e suporte)");
        Label lblNomeMaquina = new Label("Depreciação da Máquina");
        Label lblNomeEnergia = new Label("Energia Elétrica");
        Label lblNomeManutencao = new Label("Manutenção da Máquina");
        Label lblNomeMaoObra = new Label("Mão de Obra");
        Label lblNomeTotal = new Label("CUSTO TOTAL");
        Label lblNomeVenda = new Label("Valor de venda sugerido");

        // Bloqueio do corte (...) em todas as Labels da Coluna 0
        Label[] nomesItens = {lblNomeMaterial, lblNomeMaquina, lblNomeEnergia, lblNomeManutencao, lblNomeMaoObra, lblNomeTotal, lblNomeVenda};
        for (Label lbl : nomesItens) {
            lbl.setWrapText(true);
            lbl.setMinWidth(Region.USE_PREF_SIZE); // Trava absoluta: proíbe encolher e exibir "..."
        }

        direito.add(lblNomeMaterial, 0, 1);
        direito.add(lblNomeMaquina, 0, 2);
        direito.add(lblNomeEnergia, 0, 3);
        direito.add(lblNomeManutencao, 0, 4);
        direito.add(lblNomeMaoObra, 0, 5);
        direito.add(lblNomeTotal, 0, 6);
        direito.add(lblNomeVenda, 0, 7);

        // LABELS DE DETALHAMENTO DINÂMICO (Coluna 1)
        Label lblDetMaterial = new Label();
        Label lblDetMaquina = new Label();
        Label lblDetEnergia = new Label();
        Label lblDetManutencao = new Label();
        Label lblDetMaoObra = new Label();

        Label[] detalhes = {lblDetMaterial, lblDetMaquina, lblDetEnergia, lblDetManutencao, lblDetMaoObra};
        for (Label lbl : detalhes) {
            lbl.setWrapText(true);
            lbl.setMinWidth(Region.USE_PREF_SIZE);
        }

        direito.add(lblDetMaterial, 1, 1);
        direito.add(lblDetMaquina, 1, 2);
        direito.add(lblDetEnergia, 1, 3);
        direito.add(lblDetManutencao, 1, 4);
        direito.add(lblDetMaoObra, 1, 5);

        // LABELS DE VALOR MONETÁRIO FINAL (Coluna 2)
        Label lblCustoMaterial = new Label();
        Label lblCustoMaquina = new Label();
        Label lblCustoEnergia = new Label();
        Label lblCustoManutencao = new Label();
        Label lblCustoMaoObra = new Label();
        Label lblCustoTotal = new Label();
        Label lblValorVenda = new Label();

        Label[] custos = {lblCustoMaterial, lblCustoMaquina, lblCustoEnergia, lblCustoManutencao, lblCustoMaoObra, lblCustoTotal, lblValorVenda};
        for (Label lbl : custos) {
            lbl.setWrapText(true);
            lbl.setMinWidth(Region.USE_PREF_SIZE);
        }

        // Classes do CSS para estilizar as cores das fontes
        lblCustoMaterial.getStyleClass().add("custo-material-linha");
        lblCustoMaquina.getStyleClass().add("custo-maquina-linha");
        lblCustoEnergia.getStyleClass().add("custo-energia-linha");
        lblCustoManutencao.getStyleClass().add("custo-manutencao-linha");
        lblCustoMaoObra.getStyleClass().add("custo-mao-obra-linha");
        lblCustoTotal.getStyleClass().add("custo-total");
        lblValorVenda.getStyleClass().add("valor-venda");

        direito.add(lblCustoMaterial, 2, 1);
        direito.add(lblCustoMaquina, 2, 2);
        direito.add(lblCustoEnergia, 2, 3);
        direito.add(lblCustoManutencao, 2, 4);
        direito.add(lblCustoMaoObra, 2, 5);
        direito.add(lblCustoTotal, 2, 6);
        direito.add(lblValorVenda, 2, 7);

        // Alinhamento vertical ao Topo para que os textos longos cresçam de cima para baixo uniformemente
        GridPane.setValignment(lblNomeMaterial, VPos.TOP);   GridPane.setValignment(lblDetMaterial, VPos.TOP);   GridPane.setValignment(lblCustoMaterial, VPos.TOP);
        GridPane.setValignment(lblNomeMaquina, VPos.TOP);    GridPane.setValignment(lblDetMaquina, VPos.TOP);    GridPane.setValignment(lblCustoMaquina, VPos.TOP);
        GridPane.setValignment(lblNomeEnergia, VPos.TOP);    GridPane.setValignment(lblDetEnergia, VPos.TOP);    GridPane.setValignment(lblCustoEnergia, VPos.TOP);
        GridPane.setValignment(lblNomeManutencao, VPos.TOP); GridPane.setValignment(lblDetManutencao, VPos.TOP); GridPane.setValignment(lblCustoManutencao, VPos.TOP);
        GridPane.setValignment(lblNomeMaoObra, VPos.TOP);    GridPane.setValignment(lblDetMaoObra, VPos.TOP);    GridPane.setValignment(lblCustoMaoObra, VPos.TOP);
        GridPane.setValignment(lblNomeTotal, VPos.TOP);      GridPane.setValignment(lblCustoTotal, VPos.TOP);
        GridPane.setValignment(lblNomeVenda, VPos.TOP);      GridPane.setValignment(lblValorVenda, VPos.TOP);

        // Ação do Botão Calcular
        btnCalcular.setOnAction(e -> {
            try {
                double pesoModelo = Double.parseDouble(txtPeso.getText());
                double pesoSuporte = Double.parseDouble(txtSuporte.getText());
                int quantidadePecas = Integer.parseInt(txtQuantidadePecas.getText());
                double tempoImpressao = Double.parseDouble(txtTempo.getText());
                double tempoPreparo = Double.parseDouble(txtPreparo.getText());
                double taxaFalha = Double.parseDouble(txtTaxaFalha.getText());
                double valorMaoObra = Double.parseDouble(txtMaoObra.getText());
                double margemLucro = Double.parseDouble(txtMargem.getText());

                MaterialImpressao materialSelecionado = comboDensidade.getValue();
                Impressora3D impressoraSelecionada = comboImpressora.getValue();
                double valorKwh = rbDiurna.isSelected() ? 0.95 : 0.65;

                ProjetoImpressao projeto = new ProjetoImpressao(
                        materialSelecionado, txtArquivo.getText(), txtDescricao.getText(),
                        pesoModelo, pesoSuporte, quantidadePecas, tempoImpressao,
                        tempoPreparo, taxaFalha, valorMaoObra, margemLucro);

                CalculadoraCusto calc = new CalculadoraCusto();
                
                // Variáveis base de cálculo para as Strings de Detalhe
                double totalGramaComPerda = (pesoModelo + pesoSuporte) * (1 + (taxaFalha / 100.0));
                double horasVidaUtil = 2 * 365 * 4; // 4 horas diárias base
                double custoHoraMaq = impressoraSelecionada.getPreco() / horasVidaUtil;
                double consumoKwhTotal = (impressoraSelecionada.getPotenciaWatts() / 1000.0) * tempoImpressao;

                // 1. Textos de Detalhes Matemáticos por Linha (Coluna 1)
                lblDetMaterial.setText(String.format("(%.0fg + %.0fg) x %.2f = %.0fg\n%.0fg x R$ %.2f", 
                        pesoModelo, pesoSuporte, (1 + (taxaFalha/100.0)), totalGramaComPerda, totalGramaComPerda, materialSelecionado.getCustoGrama()));
                
                lblDetMaquina.setText(String.format("R$ %,.2f / 2 anos (%.0f h)\n= R$ %.2f por hora\n%.2f x %.0f h", 
                        impressoraSelecionada.getPreco(), horasVidaUtil, custoHoraMaq, custoHoraMaq, tempoImpressao));
                
                lblDetEnergia.setText(String.format("(%.0fW / 1000) x %.0f h = %.2f kWh\n%.2f x R$ %.2f", 
                        impressoraSelecionada.getPotenciaWatts(), tempoImpressao, consumoKwhTotal, consumoKwhTotal, valorKwh));
                
                lblDetManutencao.setText(String.format("R$ 0,50 por hora x %.0f h", tempoImpressao));
                
                lblDetMaoObra.setText(String.format("(%.0f h imp. + %.0f h prep.)\nx R$ %.2f", 
                        tempoImpressao, tempoPreparo, valorMaoObra));

                // 2. Valores Financeiros Finais Calculados (Coluna 2)
                double custoTotal = calc.calcularCustoTotal(projeto, impressoraSelecionada, 8, valorKwh);
                lblCustoMaterial.setText("R$ " + String.format("%.2f", calc.calcularCustoMaterial(projeto)));
                lblCustoMaquina.setText("R$ " + String.format("%.2f", calc.calcularCustoMaquina(impressoraSelecionada, projeto, 8)));
                lblCustoEnergia.setText("R$ " + String.format("%.2f", calc.calcularCustoEnergia(impressoraSelecionada, projeto, valorKwh)));
                lblCustoManutencao.setText("R$ " + String.format("%.2f", 0.50 * tempoImpressao));
                lblCustoMaoObra.setText("R$ " + String.format("%.2f", calc.calcularCustoMaoDeObra(projeto)));
                lblCustoTotal.setText("R$ " + String.format("%.2f", custoTotal));
                lblValorVenda.setText("R$ " + String.format("%.2f", custoTotal * (1.0 + (margemLucro / 100.0))));
                
            } catch (Exception ex) {
                System.out.println("Erro ao calcular. Verifique se os campos numéricos estão corretos.");
            }
        });

        // Configuração do Scroll e Janela Principal
        VBox esquerdo = new VBox(20, secao1, secao2);
        esquerdo.setPadding(new Insets(10));

        ScrollPane scrollEsquerdo = new ScrollPane(esquerdo);
        scrollEsquerdo.setFitToWidth(true);

        HBox raiz = new HBox(30, scrollEsquerdo, direito);
        raiz.setPadding(new Insets(25));
        
        // Janela alargada para acomodar as tabelas sem empurrar componentes para fora
        Scene scene = new Scene(raiz, 1420, 880);
        scene.getStylesheets().add("file:src/resources/style.css");
        stage.setTitle("Calculadora de Custos - Impressão 3D");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}