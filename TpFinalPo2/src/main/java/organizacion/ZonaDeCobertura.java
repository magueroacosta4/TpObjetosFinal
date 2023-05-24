package organizacion;

import java.util.List;

import muestra.PaginaWeb;
import muestra.PostMuestra;
import muestra.Ubicacion;

public class ZonaDeCobertura {

	private String nombre;
	private Ubicacion epicentro;
	private int radioEnKM;
	private PaginaWeb paginaWeb;
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radioEnKM, PaginaWeb unaPaginaWeb) {
		this.setNombre(nombre);
		this.setEpicentro(epicentro);
		this.setRadioEnKM(radioEnKM);
		this.setPaginaWeb(unaPaginaWeb);
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
		// TODO Auto-generated method stub
		
	}

	public void suscribirACarga(Organizacion organizacion) {
		// TODO Auto-generated method stub
		
	}

	public void deuscribirDeValidacion(Organizacion organizacion) {
		// TODO Auto-generated method stub
		
	}

	public void deuscribirDeCarga(Organizacion organizacion) {
		// TODO Auto-generated method stub
		
	}

	public boolean contieneAlPost(PostMuestra post) {
		//
		//
		// PREGUNTA SI EL POST DADO PERTENECE AL AREA DE LA ZONA
		//
		//
		return false; // despues cambia el retorno
	}

	public void ejecutarFuncionalidadValidacionASuscriptores() {
		// 
		// ES EL METODO POR EL CUAL SE LES AVISA A LOS SUSCRIPTORES QUE EJECTUTEN LA FUNCIONALIDAD
		// DE VALIDACION
		// 
		
	}

	public void ejecutarFuncionalidadDeCargaASuscriptores() {
		// 
		// ES EL METODO POR EL CUAL SE LES AVISA A LOS SUSCRIPTORES QUE EJECTUTEN LA FUNCIONALIDAD
		// DE CARGA
		// 

	}



}
