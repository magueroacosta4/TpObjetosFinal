package usuario;

public class EstadoBasico extends EstadoUsuario {
	
	public boolean esExperto() {
		return false;
	}

	@Override
	public void actualizarEstado(Usuario usuario) {
		if(usuario.getCantPosts30Dias() >= 10 &&
			usuario.getCantOpiniones30Dias() >= 20) {
			usuario.setEstado(new EstadoExperto());
		}
	}
}
