package usuario;

public abstract interface EstadoUsuario {
	
	public abstract void actualizarEstado(Usuario usuario);
	
	public abstract boolean esExperto();
}
