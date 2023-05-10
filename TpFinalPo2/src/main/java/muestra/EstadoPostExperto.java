package muestra;

public class EstadoPostExperto extends EstadoDePost {
	
	@Override
	public void opinar(VerificadorMuestra verificador, Revision revision) {
		verificador.opinarEnEstadoExperto(revision);
	}
}
