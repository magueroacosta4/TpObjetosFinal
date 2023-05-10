package organizacion;

import muestra.Ubicacion;

public class Organizacion {

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer cantidadTrabajadores;

	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, int cantidadTrabajadores) {
		setUbicacion(ubicacion);
		setTipo(tipo);
		setCantidadTrabajadores(cantidadTrabajadores);
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

}
