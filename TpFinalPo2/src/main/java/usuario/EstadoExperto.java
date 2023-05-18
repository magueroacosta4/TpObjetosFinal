package usuario;

public class EstadoExperto extends EstadoUsuario {
	
	public boolean esExperto() {
		return true;
	}

	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.getCantPosts30Dias() <= 10 &&
			usuario.getCantOpiniones30Dias() <= 20) {
			usuario.setEstado(new EstadoBasico());
		}
	}
}