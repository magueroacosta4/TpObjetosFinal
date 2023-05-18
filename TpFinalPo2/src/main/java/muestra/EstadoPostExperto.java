package muestra;

public class EstadoPostExperto extends EstadoDePost {
	
	public EstadoPostExperto(VerificadorMuestra verificador) {
		super(verificador);
	}
	
	@Override
	public void opinar(Revision revision) {
		verificador.opinarEnEstadoExperto(revision);
	}
	
	@Override
	public void verificarPost() {
		verificador.verificarPost();
	}
	
}
