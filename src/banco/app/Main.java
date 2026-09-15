package banco.app;

import java.util.Scanner;

import banco.model.Cliente;
import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;

public class Main {
    static Conta[] contasDoBanco = new Conta[50]; //Uma array de 50 posições para guardar cada nova conta.
    static int totalContas = 0; //Contabiliza quantas contas tem e qual seria a numeração da próxima.
    static Scanner teclado = new Scanner(System.in);
    static Conta contaLogada = null; //null declara que *ainda* não tem nada atribuido ao objeto.

    //Criação do metodo de cadastro para chamar no menu interativo.
    public static void cadastrarCliente() {

        if (totalContas >= contasDoBanco.length) {
            System.out.println("Aviso: O Banco Olindense está com o limite máximo de clientes lotado!");
            return;
        }
        //Usei o construtor sem dados para mandar os dados do atributo com input 1 por 1 sem precisar declara-las antes.
        Cliente user1 = new Cliente();
        boolean emailValido = false;
        boolean cpfValido = false;
        boolean senhaValida = false;
        boolean telefoneValido = false;
        //COLOCAR DESAFIO
        System.out.println("\n=== TELA DE CADASTRO ===");
        System.out.print("Digite seu nome completo: ");
        user1.setNome(teclado.nextLine());
        while (!emailValido) {
            try {
                System.out.print("Digite seu endereço de email - Exemplo --> usuario@dominio.com: ");
                user1.setEmail(teclado.nextLine());
                emailValido = true; //Só chega aqui se o setter não explodir!
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
        while (!cpfValido) {
            try {
                System.out.print("Digite seu CPF: ");
                user1.setCpf(teclado.nextLine());
                cpfValido = true; //Só chega aqui se o setter não explodir!
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
        while (!senhaValida) {
            try {
                System.out.print("Crie uma senha de 6 dígitos numéricos: ");
                user1.setSenha(teclado.nextInt());
                teclado.nextLine(); //Para remoção de buffer do int.
                senhaValida = true; //Só chega aqui se o setter não explodir!
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
        while (!telefoneValido) {
            try {
                System.out.print("Digite seu número de telefone: ");
                user1.setTelefone(teclado.nextLine());
                telefoneValido = true; //Só chega aqui se o setter não explodir!
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
//---------------------------------------------------------------------------------------------------------
        Conta continha1 = new Conta();
        boolean numeroContaValida = false;
        boolean agenciaValida = false;

        System.out.println("\n=== CRIAR CONTA ===");
        System.out.println("Escolha e digite: " +
                "\n1--Conta corrente" +
                "\n2--Conta poupança");
        int tipoDeConta = teclado.nextInt();
        teclado.nextLine(); //Evitar quaisquer buff com o scan int.
        while (!numeroContaValida) {
            try {
                System.out.println("Crie e digite um número de conta com 6 números.");
                continha1.setNumeroConta(teclado.nextLine());
                numeroContaValida = true;
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
        while (!agenciaValida) {
            try {
                System.out.println("Crie e digite um número de agência com 4 números.");
                continha1.setAgencia(teclado.nextLine());
                agenciaValida = true;
            } catch (IllegalArgumentException erro) {
                System.out.println(erro.getMessage());
            }
        }
        // A partir daqui, tenho apenas uma variável (contaCriada).
        //Ela está vazia e vai receber ou contaCorrente ou poupança.
        Conta contaCriada = null;

        if (tipoDeConta == 1) {
            contaCriada = new ContaCorrente(user1, continha1.getNumeroConta(), continha1.getAgencia());
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipoDeConta == 2) {
            contaCriada = new ContaPoupanca(user1, continha1.getNumeroConta(), continha1.getAgencia());
            System.out.println("Conta Poupança criada com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
        /**
         * contasDoBanco a variável da array[50]. Dentro,
         *o contabilizador das contas, e vai ser atribuído a contaCriada(corrente ou poupança).
         */
        contasDoBanco[totalContas] = contaCriada;
        totalContas++;
    }
    //Criação do metodo de login para chamar no menu interativo.

    /**
     * Foi utilizado o boolean como forma de obrigar o metodo a devolver um valor com return no fim.
     * Tipo, se eu uso um metodo void e chamo ele no metodo main, tudo roda, mas
     * o computador não entende se o cara acertou a senha ou não pra permitir o login.
     */
    public static boolean realizarLogin() {
        System.out.println("\n=== TELA DE LOGIN ===");
        System.out.println("Digite seu CPF (apenas números): ");
        String cpfDigitado = teclado.nextLine();
        System.out.println("Digite sua senha de 6 dígitos: ");
        int senhaDigitada = teclado.nextInt();
        teclado.nextLine(); //remover buffer do int

        //O for é o segurança que faz a varredura e confirma se pode ou não pode entrar.
        for (int i = 0; i < totalContas; i++) {
            //Pega a conta que ta guardada na gaveta i (o for que diz qual das 50) e atribui a contaDaVez.
            Conta contaDaVez = contasDoBanco[i];

            //Fazendo a busca
            /**
             * Novas variáveis locais de cpf e senha que seguem a seguinte busca:
             * Vai na conta da vez dentro da array, dá um getTitular e ganha acesso aos getters(dados) do usuário.
             */
            String cpfDaConta = contaDaVez.getTitular().getCpf();
            int senhaDaConta = contaDaVez.getTitular().getSenha();

            //Agora fazendo a validação do que foi digitado com o que buscou.
            /**
             * Na validação, se o cpf e a senha da conta do usuário que foram buscadas acima, baterem com o que foi digitado;
             * Atribui a conta da vez àquela contaLogada que tava como null e passa a receber a conta em login.
             * Sendo assim, a mensagem de sucesso do login aparece e retorna que esse boolean é true.
             * Caso chegue até essa validação e não bata nada, retorna uma mensagem de falso e atribue false ao boolean.
             */
            if (cpfDaConta.equals(cpfDigitado) && senhaDaConta == senhaDigitada) {
                contaLogada = contaDaVez;
                System.out.println("Login aprovado! Bem-vindo(a), " + contaDaVez.getTitular().getNome());
                return true;
            }
        }
        System.out.println("Acesso negado: CPF ou Senha incorretos.");
        return false;
    }

    //Criação do metodo de menu do login para chamar no subMenu interativo.
    public static void subMenuLogin() {
        int menu;
        do {
            System.out.println("O que você quer fazer hoje?" +
                    "\n1--Ver saldo" +
                    "\n2--Depositar" +
                    "\n3--Sacar" +
                    "\n4--Extrato" +
                    "\n5--Ficha de dados do cliente");
            menu = teclado.nextInt();
            teclado.nextLine(); //removedor de buffer
            switch (menu) {
                case 1:
                    System.out.println("Seu saldo atual é: R$ " + contaLogada.getSaldo());
                    break;
                case 2:
                    System.out.print("Digite um valor para depósito --> ");
                    double valorDeposito = teclado.nextDouble();
                    contaLogada.depositar(valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite um valor para saque --> ");
                    double valorSaque = teclado.nextDouble();
                    contaLogada.sacar(valorSaque);
                    break;
                case 4:
                    System.out.println("Em construção...");
                    break;
                case 5:
                    contaLogada.getTitular().apresentar();
                    break;
                case 6:
                    System.out.println("Fechando menu.....");
            }
        } while (menu != 6);
    }

    //Entrando no banco e escolhendo as opções. Onde o código funciona no terminal.
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("Olá, seja bem vindo ao Banco Olindense!" +
                    "\nPara começar, realize o seu cadastro. Escolha uma opção:" +
                    "\n1--Login" +
                    "\n2--Registrar" +
                    "\n3--Sair");
            opcao = teclado.nextInt();
            teclado.nextLine(); //Pra tirar o buffer que o nextInt causa.

            switch (opcao) {
                case 1:
                    if (realizarLogin()) {
                        subMenuLogin();
                    }
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    System.out.println("Fechando o sistema....");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha uma opção de 1 a 3");
                    break;
            }
        } while (opcao != 3);
    }
}
