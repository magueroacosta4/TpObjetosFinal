package organizacion;

import java.util.HashSet;
import java.util.List;

import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Ubicacion;

public class ZonaDeCobertura {

	private String nombre;
	private Ubicacion epicentro;
	private int radioEnKM;
	private PaginaWeb paginaWeb;
	private HashSet<Organizacion> subscritosAValidacion;
	private HashSet<Organizacion> subscritosACarga;
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radioEnKM, PaginaWeb unaPaginaWeb) {
		setNombre(nombre);
		setEpicentro(epicentro);
		setRadioEnKM(radioEnKM);
		setPaginaWeb(unaPaginaWeb);
		setSubscritosACarga(new HashSet<Organizacion>());
		setSubscritosAValidacion(new HashSet<Organizacion>());
	}
	
	private void setSubscritosACarga(HashSet<Organizacion> subscritosACarga) {
		this.subscritosACarga = subscritosACarga;
	}
	
	private void setSubscritosAValidacion(HashSet<Organizacion> subscritosAValidacion) {
		this.subscritosAValidacion = subscritosAValidacion;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	private void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}

	public int getRadioEnKM() {
		return radioEnKM;
	}

	private void setRadioEnKM(int radioEnKM) {
		this.radioEnKM = radioEnKM;
	}

	public PaginaWeb getPaginaWeb() {
		return paginaWeb;
	}
	
	private void setPaginaWeb(PaginaWeb paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public boolean seSolapaCon(ZonaDeCobertura zonaDeCobertura) {
		return
			!equals(zonaDeCobertura) &&
			distanciaAZona(zonaDeCobertura) <= sumaDeRadios(zonaDeCobertura);
	}

	private float distanciaAZona(ZonaDeCobertura zonaDeCobertura) {
		return epicentro.distanciaA(zonaDeCobertura.getEpicentro());
	}

	private int sumaDeRadios(ZonaDeCobertura zonaDeCobertura) {
		return getRadioEnKM()+zonaDeCobertura.getRadioEnKM();
	}

	public List<ZonaDeCobertura> getZonasSolapadas() {
		return paginaWeb
				.getZonasDeCobertura()
				.stream()
				.filter(z-> z.seSolapaCon(this))
				.toList();
	}

	public boolean tieneLaMuestra(PostMuestra unPostM) {
		return distanciaAMuesta(unPostM) <= getRadioEnKM();
	}

	private float distanciaAMuesta(PostMuestra unPostM) {
		return getEpicentro().distanciaA(unPostM.getUbicacion());
	}

	public List<PostMuestra> getMuestras() {
		return paginaWeb
				.getMuestras()
				.stream()
				.filter(pm -> this.tieneLaMuestra(pm))
				.toList();
	}

	public void suscribirAValidacion(Organizacion organizacion) {
		getSubscritosAValidacion().add(organizacion);
	}

	public HashSet<Organizacion> getSubscritosAValidacion() {
		return subscritosAValidacion;
	}
	
	public HashSet<Organizacion> getSubscritosACarga() {
		return subscritosACarga;
	}

	public void suscribirACarga(Organizacion organizacion) {
		getSubscritosACarga().add(organizacion);
	}

	public void desuscribirDeValidacion(Organizacion organizacion) {
		getSubscritosAValidacion().remove(organizacion);
	}

	public void desuscribirDeCarga(Organizacion organizacion) {
		getSubscritosACarga().remove(organizacion);
	}

	public void notificarValidacionDeMuestra(PostMuestra postMuestra) {
		getSubscritosAValidacion().forEach(o -> o.ejecutarFuncionalidadValidacion(this, postMuestra));
	}

	public void notificarCargaDeMuestra(PostMuestra posteo) {
		getSubscritosACarga().forEach(o -> o.ejecutarFuncionalidadCarga(this, posteo));
	}

}
