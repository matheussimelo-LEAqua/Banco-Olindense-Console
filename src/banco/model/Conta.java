package banco.model;

import java.util.regex.Pattern;

public abstract class Conta {
    private Cliente titular; //atributo titular que puxa e reune os atributos que a classe Cliente tem.
    private String numeroConta;
    private String agencia;
    private double saldo;

    //Construtor sem dados
    public Conta() {
    }

    //Construtor com dados
    public Conta(Cliente titular, String numeroConta, String agencia) {
        this.titular = titular;
        setNumeroConta(numeroConta); //O setNumeroConta no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
        setAgencia(agencia); //O setAgencia no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
        this.saldo = 0.0; //this.saldo = 0.0 pois dessa forma não dá o direito do usuário digitar seu saldo, ele inicia zerado.
    }

    //Getters
    public Cliente getTitular() {            //importante para pegar os dados do cliente da class Cliente.
        return titular;
    }

    public String getNumeroConta() {          //importante para imprimir extratos ou dados do numeroConta.
        return numeroConta;
    }

    public String getAgencia() {              //importante para imprimir extratos ou dados da agencia.
        return agencia;
    }

    public double getSaldo() {               //importante para imprimir o saldo atual.
        return saldo;
    }

    //Setters
    //Nesse setter(), o 'import java.util.regex.Pattern;' declara a sintaxe que o numero da conta do usuário deve ter.

    /**
     * Dentro do bloco if(), foram criadas duas Strings novas: blocoPrincipal e digito.
     * String blocoPrincipal foi atribuído para que: numeroConta.substring leia do primeiro dígito até o penúltimo e guarde.
     * String digito foi atribuído para que: numeroConta.substring leia o último dígito e guarde.
     * Logo, this.numeroConta atribui que: os 5 primeiros dígitos vem sempre somados de um hífen + o último dígito.
     *
     * @param numeroConta
     */
    public void setNumeroConta(String numeroConta) {
        String padrao = "^\\d{6}$";
        if (Pattern.matches(padrao, numeroConta)) {
            this.numeroConta = numeroConta;
        } else {
            throw new IllegalArgumentException("Número de conta inválido. Digite exatamente 6 números.");
        }

    }

    public void setAgencia(String agencia) {
        String padrao = "^\\d{4}$";
        if (Pattern.matches(padrao, agencia)) {
            this.agencia = agencia;
        } else {
            throw new IllegalArgumentException("Agência inválida. Digite exatamente 4 números.");
        }
    }

    //Métodos
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Deposito realizado com sucesso!");
        } else {
            throw new IllegalArgumentException("Valor inválido. Digite um valor acima de 0.");
        }

    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else if (valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        } else {
            throw new IllegalArgumentException("Valor inválido. O saque deve ser maior que 0.");
        }
    }

    //Esse metodo foi criado na intenção de poder alterar o saldo nas operações de atribuição lá em contaCorrente e poupança, já que não se atribui nada a um getter.
    protected void ajustarSaldo(double valor) {

        this.saldo += valor;
    }

    public void apresentar() {
        System.out.println("------Impressão de dados da conta------\n" +
                "Cliente: " + titular.getNome() + " \n" +
                "CPF: " + titular.getCpf() + " \n" +
                "Número da conta: " + this.numeroConta + " \n" +
                "Agência: " + this.agencia + " \n" +
                "Saldo: " + this.saldo + "\n-----------------------------------");
    }
}