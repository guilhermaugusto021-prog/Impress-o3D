/**
 * Representa uma impressora 3D cadastrada no sistema.
 */
public class Impressora3D {

    private String modelo;
    private double preco;
    private double potenciaWatts;
    private String imagemPath;
    private String descricao;

    public Impressora3D(
            String modelo,
            double preco,
            double potenciaWatts,
            String imagemPath,
            String descricao) {

        this.modelo = modelo;
        this.preco = preco;
        this.potenciaWatts = potenciaWatts;
        this.imagemPath = imagemPath;
        this.descricao = descricao;
    }

    // ===== GETTERS =====

    public String getModelo() {
        return modelo;
    }

    public double getPreco() {
        return preco;
    }

    public double getPotenciaWatts() {
        return potenciaWatts;
    }

    public String getImagemPath() {
        return imagemPath;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return modelo;
    }
}