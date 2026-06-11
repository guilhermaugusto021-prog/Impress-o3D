/**
 * Representa um material de impressão
 * contendo nome e custo por grama.
 */
public class MaterialImpressao {

    private String nome;
    private double custoGrama;

    public MaterialImpressao(
            String nome,
            double custoGrama) {

        this.nome = nome;
        this.custoGrama = custoGrama;
    }

    public String getNome() {
        return nome;
    }

    public double getCustoGrama() {
        return custoGrama;
    }

    @Override
    public String toString() {
        return nome;
    }
}