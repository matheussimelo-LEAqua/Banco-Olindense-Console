package banco.model;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    //Construtor
    public ContaCorrente(Cliente titular, String numeroConta, String agencia) {
        super(titular, numeroConta, agencia);
        this.limiteChequeEspecial = 500.00;
    }

    //Getters
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    //Setters - herdados

    //Métodos
    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.getSaldo() + limiteChequeEspecial) {
            ajustarSaldo(-valor); // sinal negativo junto a valor pra mudar a operação de soma que foi definida em conta e realizar a retirada pra saque.
            System.out.println("Saque realizado com sucesso!");
        } else if (valor > this.getSaldo() + limiteChequeEspecial) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            System.out.println("Valor inválido. O saque deve ser maior que 0."); //caso o usuário digite um valor negativo
        }
    }
    @Override
    public void apresentar() {
        super.apresentar(); // roda o apresentar() da Conta base primeiro
        System.out.println("Limite do cheque especial: " + this.limiteChequeEspecial + "\n---------------------------------");
    }
}

