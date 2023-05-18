package usuario;

public class EstadoExperto extends EstadoUsuario {

	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.getCantPosts30Dias() <= 10 &&
			usuario.getCantOpiniones30Dias() <= 20) {
			usuario.setEstado(new EstadoBasico());
		}
	}

	@Override
	public boolean esExperto() {
		return true;
	}
}