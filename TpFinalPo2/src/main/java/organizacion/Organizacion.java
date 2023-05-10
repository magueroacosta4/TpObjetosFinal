package organizacion;

import muestra.PaginaWeb;
import muestra.Ubicacion;

public class Organizacion {

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer cantidadTrabajadores;
	private PaginaWeb paginaWeb;

	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, int cantidadTrabajadores, PaginaWeb paginaWeb) {
		setUbicacion(ubicacion);
		setTipo(tipo);
		setCantidadTrabajadores(cantidadTrabajadores);
		setPaginaWeb(paginaWeb);
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public TipoOrganizacion getTipo() {
		return tipo;
	}
	
	private void setTipo(TipoOrganizacion tipo) {
		this.tipo = tipo;
	}

	public int getCantidadTrabajadores() {
		return cantidadTrabajadores;
	}
	
	private void setCantidadTrabajadores(Integer cantidadTrabajadores) {
		this.cantidadTrabajadores = cantidadTrabajadores;
	}
	
	private void setPaginaWeb(PaginaWeb paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public PaginaWeb getPaginaWeb() {
		return paginaWeb;
	}
}
