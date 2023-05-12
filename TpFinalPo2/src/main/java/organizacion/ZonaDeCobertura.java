package organizacion;

import java.util.Arrays;
import java.util.List;

import muestra.PaginaWeb;
import muestra.Ubicacion;

public class ZonaDeCobertura {

	String nombre;
	Ubicacion epicentro;
	int radioEnKM;
	PaginaWeb paginaWeb;
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radioEnKM, PaginaWeb unaPaginaWeb) {
		setNombre(nombre);
		setEpicentro(epicentro);
		setRadioEnKM(radioEnKM);
		setPaginaWeb(unaPaginaWeb);
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

	private int distanciaAZona(ZonaDeCobertura zonaDeCobertura) {
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

}
