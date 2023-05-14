package muestra;

public abstract class EstadoDePost {
	
	protected VerificadorMuestra verificador;
	
	public EstadoDePost(VerificadorMuestra verificador) {
		this.setVerificador(verificador);
	}
	
	public void setVerificador(VerificadorMuestra verificador) {
		this.verificador = verificador;
	}
	
	public VerificadorMuestra getVerificador() {
		return this.verificador;
	}

	public abstract void opinar(Revision revision) ;

	protected abstract void verificarPost();

}
