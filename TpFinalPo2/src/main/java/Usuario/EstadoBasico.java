package Usuario;

public class EstadoBasico extends EstadoUsuario {
	
	public boolean esExperto() {
		return false;
	}

	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.getPostsEnLaApp().size() >= 10 &&
			usuario.getOpinionesEnLaApp().size() >= 20) {
			usuario.setEstado(new EstadoExperto());
		}
	}
}
