package banco.model;

import java.util.regex.Pattern;

public class Cliente {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private int senha;

    //Construtor sem dados
    /**
     * Esse construtor permite que um cliente nasça sem dados.
     * O construtor abaixo desse só permite que um cliente nasça la no main com seus atributos.
     */
    public Cliente() {}

    //Construtor com dados
    public Cliente(String nome, String email, String cpf, String telefone, int senha) {
        setNome(nome); //Não tem que colocar nada, coloquei por padronização.
        setEmail(email); //O setEmail no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
        setCpf(cpf); //O setCpf no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
        setTelefone(telefone); //O setTelefone no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
        setSenha(senha); //O setSenha no construtor garante que meu regex seja validado e imprima erro em caso de sintaxe errada.
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getSenha() {
        return senha;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Nesse setter(), o 'import java.util.regex.Pattern;' declara a sintaxe que o email do usuário deve ter.
    public void setEmail(String email) {
        String padrao = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (Pattern.matches(padrao, email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Endereço de email inválido.\nExemplo válido: usuario@dominio.com");
        }
    }

    //Nesse setter(), o 'import java.util.regex.Pattern;' declara a sintaxe que o CPF do usuário deve ter.
    public void setCpf(String cpf) {
        // Exige exatamente 11 dígitos numéricos, sem pontos ou traços
        String padrao = "^\\d{11}$";
        if (Pattern.matches(padrao, cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido. Digite apenas os 11 números (ex: 12345678900)");
        }
    }

    //Nesse setter(), o 'import java.util.regex.Pattern;' declara a sintaxe que o telefone do usuário deve ter.
    public void setTelefone(String telefone) {
        String padrao = "^\\d{11}$";
        if (Pattern.matches(padrao, telefone)) {
            this.telefone = telefone;
        } else {
           throw new IllegalArgumentException("Telefone inválido. Digite apenas os 11 números, sem espaços ou símbolos (ex: 81912345678)");
        }
    }

    public void setSenha(int senha) {
        String padrao = "^\\d{6}$";
        //String.valueof() - Transforma apenas nesse momento a variável int em String pra passar no regex, que só aceita String ou char.
        if (Pattern.matches(padrao, String.valueOf(senha))) {
            this.senha = senha;
            /**
             * throw - Interrompe instantaneamente o programa.
             * new - criação da classe IllegalArgumentExcepcion()
             * IllegalArgumentExcepcion("") - Usado em ocasiões em que algo é válido ou não é,
             * permitindo colocar um print dentro dele.
              */
        } else {
            throw new IllegalArgumentException("A senha deve conter exatamente 6 dígitos numéricos.");
        }
    }

    //Métodos
    //metodo apresentar()
    public void apresentar() {
        System.out.println(
                "--- Ficha do Cliente ---\n" +
                        "Nome: " + this.nome + "\n" +
                        "CPF: " + this.cpf + "\n" +
                        "E-mail: " + this.email + "\n" +
                        "Telefone: " + this.telefone + "\n" +
                        "========================");
    }
}