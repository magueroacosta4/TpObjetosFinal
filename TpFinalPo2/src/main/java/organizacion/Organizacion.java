package organizacion;

import java.util.Optional;

import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Ubicacion;

public class Organizacion {

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer cantidadTrabajadores;
	private PaginaWeb paginaWeb;
	private Optional<FuncionalidadExterna> funcionalidadValidacion;
	private Optional<FuncionalidadExterna> funcionalidadCarga;

	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, int cantidadTrabajadores, PaginaWeb paginaWeb) {
		setUbicacion(ubicacion);
		setTipo(tipo);
		setCantidadTrabajadores(cantidadTrabajadores);
		setPaginaWeb(paginaWeb);
		setFuncionalidadValidacion(Optional.empty());
		setFuncionalidadCarga(Optional.empty());
	}
	
	private void setFuncionalidadValidacion(Optional<FuncionalidadExterna> funcionalidadValidacion) {
		this.funcionalidadValidacion = funcionalidadValidacion;
	}
	
	private void setFuncionalidadCarga(Optional<FuncionalidadExterna> funcionalidadCarga) {
		this.funcionalidadCarga = funcionalidadCarga;
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

	public Optional<FuncionalidadExterna> getFuncionalidadValidacion() {
		return funcionalidadValidacion;
	}

	public Optional<FuncionalidadExterna> getFuncionalidadCarga() {
		return funcionalidadCarga;
	}

	public void definirFuncionalidadCarga(FuncionalidadExterna funcionalidadExterna) {
		setFuncionalidadCarga(Optional.of(funcionalidadExterna));
	}
	
	public void definirFuncionalidadValidacion(FuncionalidadExterna funcionalidadExterna) {
		setFuncionalidadValidacion(Optional.of(funcionalidadExterna));
	}

	public void ejecutarFuncionalidadValidacion(ZonaDeCobertura unaZonaDeCobertura, PostMuestra unPostMuestra) {
		getFuncionalidadValidacion().ifPresent(f -> f.nuevoEvento(this, unaZonaDeCobertura, unPostMuestra));
	}
	
	public void ejecutarFuncionalidadCarga(ZonaDeCobertura unaZonaDeCobertura, PostMuestra unPostMuestra) {
		getFuncionalidadCarga().ifPresent(f -> f.nuevoEvento(this, unaZonaDeCobertura, unPostMuestra));
	}

	public void suscribirseAValidacionEn(ZonaDeCobertura unaZona) {
		unaZona.suscribirAValidacion(this);
	}

	public void suscribirseACargaEn(ZonaDeCobertura unaZona) {
		unaZona.suscribirACarga(this);
	}

	public void desuscribirseDeValidacionEn(ZonaDeCobertura unaZona) {
		unaZona.desuscribirDeValidacion(this);
	}
	
	public void desuscribirseDeCargaEn(ZonaDeCobertura unaZona) {
		unaZona.desuscribirDeCarga(this);
	}
}
