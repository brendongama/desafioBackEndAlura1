package br.com.desafio.model;

public enum Categoria {
	
	ALIMENTACAO(0, "ALIMENTACAO"), 
	SAUDE(1, "SAUDE"), 
	MORADIA(2, "MORADIA"),
	TRANSPORTE(3, "TRANSPORTE"),
	EDUCACAO(4, "EDUCACAO"),
	LAZER(5, "LAZER"),
	IMPREVISTOS(6, "IMPREVISTOS"),
	OUTRAS(7, "OUTRAS");

	private Integer codigo;
	private String descricao;
	
	private Categoria(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Categoria toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Categoria x : Categoria.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioridade inválida");

	}
	

}
