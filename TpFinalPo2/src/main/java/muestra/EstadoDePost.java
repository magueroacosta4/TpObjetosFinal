package muestra;

public abstract class EstadoDePost {
	
	protected PostMuestra post;

	public EstadoDePost(PostMuestra post) {
		this.post = post;
	}
	
	public PostMuestra getPost() {
		return post;
	}

	public abstract void opinar(Revision revision, VerificadorMuestra verificadorMuestra) ;

	protected abstract void verificarPost();

	public abstract boolean esVerificado();


}
