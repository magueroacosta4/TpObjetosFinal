package muestra;

public class EstadoPostBasico extends EstadoDePost {
	
	@Override
	public void opinar(VerificadorMuestra verificador, Revision revision) {
		verificador.opinarEnEstadoBasico(revision);
	}
}
