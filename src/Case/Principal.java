package Case;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;


public class Principal {
    public static void main(String[] args) {
    	List<Funcionario> funcionarios = new ArrayList<>();
    	funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
    	funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"),"Operador"));
    	funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"),"Coordenador"));
    	funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19199.88"),"Diretor"));
    	funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"),"recepcionista"));
    	funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        funcionarios.removeIf(x->x.getNome().equals("Joao"));//retira joão da lista, se nome for joão vai remover do array funcionarios
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");//formata a  data para dia mes e ano
        Locale localeBR=new Locale("pt","BR"); //indica qual o local onde  estamos  para  seguir com os padroes e formatações
        System.out.println("Funcionarios");//cabeçalho da escrita
        for (Funcionario f:funcionarios) {
        	System.out.println((String.format(localeBR, "Nome: %s, Data de Nascimento: %s, Salário: %.2f, Função: %s",
                    f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario(), f.getFuncao())));
        	
        }//percorre a lista de funcionarios com um foreach e escreve de acordo com o funcionario percorrido, %s para string,%.2f para decimais com 2 casas, %d para inteiros
        for(Funcionario f: funcionarios) {
        	f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10)));
        }//percorre o array com anteriormente mas usa a biblioteca com as funcoes .multiply para multiplicar o salario de cada funcionario por 1.10 que seria um aumento de 10%
        		
     
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
     
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println(String.format(localeBR, "  Nome: %s, Data de Nascimento: %s, Salário: %,.2f",
                    f.getNome(), f.getDataNascimento().format(dateFormatter), f.getSalario())));
        });
     
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println(String.format("Nome: %s, Data de Nascimento: %s",
                        f.getNome(), f.getDataNascimento().format(dateFormatter)));
            }//percorre a lista de funcionarios e mostra na tela a frase usando o  .format apenas quando o mes da data  de aniversario é 10 ou 12
        }

        Funcionario funcionarioMaisVelho = null;//objeto de referencia
        for (Funcionario f : funcionarios) {
            System.out.println(f.getDataNascimento());
            if (funcionarioMaisVelho == null || f.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = f;
            }//percorre a lista adicionando um funcionario ao funcionariomaisvelho caso ele esteja vazio, assim no proximo loop  caso o funcionario a tual seja mais velho que o anterior o mesmo é adicionado tomando a posicao de funcionario mais velho
            
        }
        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho: " + funcionarioMaisVelho.getNome() + ", Data de Nascimento: " + funcionarioMaisVelho.getDataNascimento());
        }//imprime o funcionario mais velho que foi encontrado anteriormente caso o objeto de referencia nao esteja nulo 
        
        

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários por ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .forEach(f -> System.out.println(f.getNome()));

        BigDecimal total = BigDecimal.ZERO; // Inicializa o total com zero

        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario()); // Usa o metodo add para somar os salários
        }

        
        System.out.println("Total dos salários: " + total);//imprime o salario total


        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(String.format(localeBR, "Nome: %s, Salários Mínimos: %,.2f",
                    f.getNome(), qtdSalariosMinimos));
        }//percorre o array com o for usando o bigdecimal para setar o salario minimo e depois cria uma nova variavel com o nome de  qtdSalariosMinimos a qual vai receber o valor dos salarios divididos pelo valor da variavel salarioMinimo
    
    
    
    }
        	
        
    }
       

// das linhas  39 a 47 que é onde se encontram as atividades 3.5 e 3.6 entendi completamente o codigo mas realizei o mesmo com ajuda do meu professor do senai e consultas na internet pois não sabia como  funcionavam taivs ferramente de stream e agrupamento, porem como dito, compreendi completamente a sintaxe e com um pouco mais de pesquisas conseguiria aplicar. 


