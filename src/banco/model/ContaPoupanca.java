package banco.model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    //Construtor
    public ContaPoupanca(Cliente titular, String numeroConta, String agencia) {
        super(titular, numeroConta, agencia);
        this.taxaRendimento = 0.01;
    }

    //Getters
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    //Setters - herdado

    //Métodos
    public void renderJuros() {
        if (this.getSaldo() > 0){
            double rendimento = this.getSaldo() * this.taxaRendimento; //calcula quanto rendeu e atribui a nova variavel rendimento.
            ajustarSaldo(rendimento); //soma o quanto rendeu ao saldo -- positivo pois está acrescentando.
            System.out.println("Seu saldo está com um rendimento de: " + (this.taxaRendimento * 100) + "%, \n" +
                    "Rendeu: R$" + rendimento + " \n" +
                    "Saldo atual é de: " + this.getSaldo());
        } else {
            System.out.println("A conta não possui saldo para render juros.");
        }
    }
    @Override
    public void apresentar() {
        super.apresentar(); // roda o apresentar() da classe Conta primeiro.
        System.out.println("Taxa de rendimento: " + this.taxaRendimento + "\n---------------------------------");
    }
}
