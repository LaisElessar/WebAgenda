package modelBean;

public class JavaBeans {
	private Integer idcon;//integer
	private String nome;//varchar(50)
	private String fone;//varchar(50)
	private String email;//varchar(50)
		
	public JavaBeans(Integer idcon, String nome, String fone, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	public JavaBeans() {
		super();
	}
	public Integer getIdcon() {
		return idcon;
	}
	public void setIdcon(Integer idcon) {
		this.idcon=idcon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome=nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone=fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
}
