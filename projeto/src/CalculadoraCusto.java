/**
 * Responsável por realizar todos os cálculos financeiros
 * relacionados ao projeto de impressão 3D com suporte completo a lotes.
 */
public class CalculadoraCusto {

    /**
     * Calcula o custo do material considerando: (Peso Modelo + Peso Suporte) * Quantidade * Taxa de Falha
     */
    public double calcularCustoMaterial(ProjetoImpressao projeto) {
        if (projeto.getMaterial() == null) return 0.0;
        
        // Peso total previsto para todas as peças do lote (incluindo suportes)
        double pesoTotalLote = (projeto.getPesoModelo() + projeto.getPesoSuporte()) * projeto.getQuantidadePecas();
        
        // Aplica a taxa de falha (Ex: se for 10%, multiplica por 1.10)
        double pesoComFalha = pesoTotalLote * (1.0 + (projeto.getTaxaFalha() / 100.0));
        
        return pesoComFalha * projeto.getMaterial().getCustoGrama();
    }

    /**
     * Calcula o custo de depreciação real da máquina proporcional ao tempo de uso total do lote.
     */
    public double calcularCustoMaquina(Impressora3D impressora, ProjetoImpressao projeto, double horasPorDia) {
        if (impressora == null) return 0.0;

        // Horas totais de vida útil estimada do equipamento (2 anos)
        double horasTotaisVidaUtil = 2.0 * 184 * horasPorDia;
        double custoHoraMaquina = impressora.getPreco() / horasTotaisVidaUtil;

        // MULTIPLICADOR DO LOTE: Tempo de impressão unitário multiplicado pela quantidade de peças do lote
        double tempoTotalImpressaoLote = projeto.getTempoImpressao() * projeto.getQuantidadePecas();

        return custoHoraMaquina * tempoTotalImpressaoLote;
    }

    /**
     * Calcula o custo de energia elétrica gasta para confeccionar o lote inteiro.
     */
    public double calcularCustoEnergia(Impressora3D impressora, ProjetoImpressao projeto, double valorKwh) {
        if (impressora == null) return 0.0;

        // Tempo acumulado de uso da resistência e motores para o lote
        double tempoTotalHoras = projeto.getTempoImpressao() * projeto.getQuantidadePecas();
        double consumoKwh = (impressora.getPotenciaWatts() / 1000.0) * tempoTotalHoras;

        return consumoKwh * valorKwh;
    }

    /**
     * Calcula a taxa de manutenção preventiva por horas rodadas no lote.
     */
    public double calcularCustoManutencao(ProjetoImpressao projeto) {
        // R$ 0,50 fixo por hora aplicada à carga de impressão total do lote
        return 0.50 * (projeto.getTempoImpressao() * projeto.getQuantidadePecas());
    }

    /**
     * Calcula o custo de mão de obra direta consumida na operação do lote.
     */
    public double calcularCustoMaoDeObra(ProjetoImpressao projeto) {
        // Considera o tempo de fatiamento/preparo e execução multiplicados pelo número de peças do lote
        double tempoTotalLote = (projeto.getTempoImpressao() + projeto.getTempoPreparo()) * projeto.getQuantidadePecas();
        return tempoTotalLote * projeto.getValorMaoObra();
    }

    /**
     * Totaliza o custo combinado do lote de fabricação.
     */
    public double calcularCustoTotal(ProjetoImpressao projeto, Impressora3D impressora, double horasPorDia, double valorKwh) {
        return calcularCustoMaterial(projeto) 
             + calcularCustoMaquina(impressora, projeto, horasPorDia) 
             + calcularCustoEnergia(impressora, projeto, valorKwh) 
             + calcularCustoMaoDeObra(projeto) 
             + calcularCustoManutencao(projeto);
    }
}