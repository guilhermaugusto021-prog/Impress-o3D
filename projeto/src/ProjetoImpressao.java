/**
 * Armazena todas as informações
 * referentes ao projeto de impressão.
 */
public class ProjetoImpressao {

    private String arquivoModelo;
    private String descricao;

    private double pesoModelo;
    private double pesoSuporte;

    private int quantidadePecas;

    private double tempoImpressao;
    private double tempoPreparo;

    private double taxaFalha;
    private double valorMaoObra;
    private double margemLucro;

    private MaterialImpressao material;

    public ProjetoImpressao(
            MaterialImpressao material,
            String arquivoModelo,
            String descricao,
            double pesoModelo,
            double pesoSuporte,
            int quantidadePecas,
            double tempoImpressao,
            double tempoPreparo,
            double taxaFalha,
            double valorMaoObra,
            double margemLucro) {

        this.material = material;
        this.arquivoModelo = arquivoModelo;
        this.descricao = descricao;
        this.pesoModelo = pesoModelo;
        this.pesoSuporte = pesoSuporte;
        this.quantidadePecas = quantidadePecas;
        this.tempoImpressao = tempoImpressao;
        this.tempoPreparo = tempoPreparo;
        this.taxaFalha = taxaFalha;
        this.valorMaoObra = valorMaoObra;
        this.margemLucro = margemLucro;
    }

    public MaterialImpressao getMaterial() { return material; }
    public String getArquivoModelo() { return arquivoModelo; }
    public String getDescricao() { return descricao; }
    public double getPesoModelo() { return pesoModelo; }
    public double getPesoSuporte() { return pesoSuporte; }
    public int getQuantidadePecas() { return quantidadePecas; }
    public double getTempoImpressao() { return tempoImpressao; }
    public double getTempoPreparo() { return tempoPreparo; }
    public double getTaxaFalha() { return taxaFalha; }
    public double getValorMaoObra() { return valorMaoObra; }
    public double getMargemLucro() { return margemLucro; }
}