package muestra;

public class EstadoPostExperto extends EstadoDePost {
	
	public EstadoPostExperto(PostMuestra post) {
		super(post);
	}

	@Override
	public void opinar(Revision revision, VerificadorMuestra verificador) {
		verificador.opinarEnEstadoExperto(revision);
	}
	
	@Override
	public void verificarPost() {
		this.getPost().verificarPost();
	}	
}
