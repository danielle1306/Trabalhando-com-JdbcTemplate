package danielle.banco.de.dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import danielle.banco.de.dados.domain.entity.Cliente;
import danielle.banco.de.dados.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			 System.out.println("Salvando clientes");
	            clientes.salvar(new Cliente("Danielle"));
	            clientes.salvar(new Cliente("Outro Cliente"));

	            List<Cliente> todosClientes = clientes.obterTodos();//coloco na var obtertodos
	            todosClientes.forEach(System.out::println);
	            //foreach vai percorrer todos os clientes
	            //::é um método de reference que imprime a string recebida por oforeach, no caso, o cliente crio o met tostring em cliente

	            System.out.println("Atualizando clientes");
	            todosClientes.forEach(c -> {//para cada cliente
	                c.setNome(c.getNome() + " atualizado.");//pego o nome dele mesmo mas a frase atualizado pra ver se atualizou
	                clientes.atualizar(c);//esse é o métdo atualizar o cliente
	            });

	            todosClientes = clientes.obterTodos();
	            todosClientes.forEach(System.out::println);

	            System.out.println("Buscando clientes");
	            clientes.buscarPorNome("Cli").forEach(System.out::println);//vai encontrar o cliente de id 2 "outro CLIente" e vai imprimir o resultado da pesquisa

//	            System.out.println("deletando clientes");
//	            clientes.obterTodos().forEach(c -> {//para cada cliente que encontrar, deletar
//	                clientes.deletar(c);//c = cliente
//	            });

	            todosClientes = clientes.obterTodos();//pesquiso novamente para ver a lista final. Vai estar vazia pq deletei todos
	            if(todosClientes.isEmpty()){
	                System.out.println("Nenhum cliente encontrado.");
	            }else{
	                todosClientes.forEach(System.out::println);//se nao estiver vazia, retornar a lista dos clientes exitentes
	            }
	        };
	    }

	    public static void main(String[] args) {
	        SpringApplication.run(VendasApplication.class, args);
	    }
	}
