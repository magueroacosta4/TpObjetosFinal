package filtrosDeBusqueda;

import java.time.LocalDate;
import java.util.List;

import muestra.PostMuestra;

public abstract class FiltroDeFecha extends FiltrosBusqueda {

	private LocalDate fechaReferencia;
	private String operador;
	
	protected FiltroDeFecha(LocalDate fecha, String operador) {
		this.setFechaReferencia(fecha);
		this.setOperador(operador);
	}

	@Override
	protected void aplicarCriterioDeFiltro(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		switch(this.getOperador()) {
		case "=":
			this.esLaFecha(resultadoBusqueda, post);
			break;
		case ">":
			this.despuesDeFecha(resultadoBusqueda, post);
			break;
		case "<":
			this.antesDeFecha(resultadoBusqueda, post);
			break;
		default:
			break;
		}
			
	}
	
	protected void esLaFecha(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		if(this.fechaObjetivoDePost(post).isEqual(this.getFechaReferencia())) {
			resultadoBusqueda.add(post);
		}
	}

	protected void antesDeFecha(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		if(this.fechaObjetivoDePost(post).isBefore(this.getFechaReferencia())) {
			resultadoBusqueda.add(post);
		}
	}

	
	protected void despuesDeFecha(List<PostMuestra> resultadoBusqueda, PostMuestra post) {
		if(this.fechaObjetivoDePost(post).isAfter(this.getFechaReferencia())) {
			resultadoBusqueda.add(post);
		}
	}
	
	protected abstract LocalDate fechaObjetivoDePost(PostMuestra post);

	public LocalDate getFechaReferencia() {
		return fechaReferencia;
	}

	public void setFechaReferencia(LocalDate fechaReferencia) {
		this.fechaReferencia = fechaReferencia;
	}
	
	public String getOperador() {
		return operador;
	}
	
	public void setOperador(String op) {
		this.operador = op;
	}

}
