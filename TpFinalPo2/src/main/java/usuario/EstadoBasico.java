package usuario;

public class EstadoBasico implements EstadoUsuario {
	
	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.postsUltimos30Dias() >= 10 &&
			usuario.opinionesUltimos30Dias() >= 20) {
			usuario.setEstado(new EstadoExperto());
		}
	}

	@Override
	public boolean esExperto() {
		return false;
	}
}
