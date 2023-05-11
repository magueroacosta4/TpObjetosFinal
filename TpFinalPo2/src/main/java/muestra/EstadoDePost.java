package muestra;

public abstract class EstadoDePost {
	
	protected VerificadorMuestra verificador;
	
	public EstadoDePost(VerificadorMuestra verificador) {
		this.verificador = verificador;
	}

	public abstract void opinar(Revision revision) ;

	protected abstract void verificarPost();

}
