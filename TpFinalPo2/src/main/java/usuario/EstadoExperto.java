package usuario;

public class EstadoExperto implements EstadoUsuario {

	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.postsUltimos30Dias() <= 10 &&
			usuario.opinionesUltimos30Dias() <= 20) {
			usuario.setEstado(new EstadoBasico());
		}
	}

	@Override
	public boolean esExperto() {
		return true;
	}
}