package danielle.banco.de.dados.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import danielle.banco.de.dados.domain.entity.Cliente;

@Repository
public class Clientes {
	private static String INSERT = "insert into cliente (nome) values (?)";//script do que deve ser feito.
	//só estou mudando a col nome pq o id é automático. esse é o 2o param no met update
	
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";//? vai ser o nome e o id que ainda vou passar
    private static String DELETE = "delete from cliente where id = ? ";
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;//o jdbc trabalha com sql e vai fazer as operacoes com o cliente
	
	public Cliente salvar(Cliente cliente) {
		jdbcTemplate.update(INSERT, new Object[] {cliente.getNome()});
		return cliente;
		
	}
	public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{//criar o script lá em cima
                cliente.getNome(), cliente.getId()} );//o 1o param é o nome do cliente(lá no script) e depois o id. tem que colocar na ordem certa
        return cliente;
    }

    public void deletar(Cliente cliente){//crio o script lá em cima
        deletar(cliente.getId());//vai passar o id. poderia ser o cliente
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

   public List<Cliente> buscarPorNome(String nome){//realizando uma pesquisa simples. vamos buscar um cliente pelo nome
        return jdbcTemplate.query(//vamos utilizar o jbdc e rowmapper. reaproveitamos o roww mapper e criamos um método obterclientemapper
        		//assim posso chamá-lo
                SELECT_ALL.concat(" where nome like ? "),//reaproveito e sellectall e conc com o nome. O like serve para se digitar só um pedaço do nome, já aprecer resultado
                new Object[]{"%" + nome + "%"},//o % indica que, encontrando o pedaço, ele devolve a string nome
                obterClienteMapper());
    }

    public List<Cliente> obterTodos(){//vai listar os clientes cadastrados
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());//retorna 2 param:select all que será criado como script lá em cima
        //e 2 param é o rowmapper - uma classe que mapeia o resultado do bd p/a classe com as col e valores
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");//vai trazer o valor informado na col nome
                return new Cliente(id, nome);
            }
        };
    }


}
