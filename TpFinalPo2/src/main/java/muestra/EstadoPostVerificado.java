package muestra;

public class EstadoPostVerificado extends EstadoDePost {

	public EstadoPostVerificado(PostMuestra post) {
		super(post);
	}

	@Override
	public void opinar(Revision revision, VerificadorMuestra verificador) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void verificarPost() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean esVerificado() {return true;}

}
