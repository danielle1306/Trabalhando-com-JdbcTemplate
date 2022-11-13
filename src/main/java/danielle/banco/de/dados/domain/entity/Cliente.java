package danielle.banco.de.dados.domain.entity;

import javax.persistence.Id;

public class Cliente {
	
	
	 private Integer id;
	    private String nome;

	    public Cliente() {
	    }

	    public Cliente(Integer id, String nome) {
	        this.id = id;
	        this.nome = nome;
	    }

	    public Cliente(String nome) {
	        this.nome = nome;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    @Override
	    public String toString() {//quando o foreach e o método de reference for chamado , vai imprimir esse tostring
	        return "Cliente{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                '}';
	    }
	}