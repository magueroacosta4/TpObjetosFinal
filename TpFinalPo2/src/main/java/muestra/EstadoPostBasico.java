package muestra;

public class EstadoPostBasico extends EstadoDePost {
	
	public EstadoPostBasico(VerificadorMuestra verificador) {
		super(verificador);
	}
	
	@Override
	public void opinar(Revision revision) {
		verificador.opinarEnEstadoBasico(revision);
	}

	@Override
	protected void verificarPost() {}


	
}
