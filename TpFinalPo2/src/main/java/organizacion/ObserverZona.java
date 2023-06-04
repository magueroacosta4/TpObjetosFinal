package organizacion;

import muestra.PostMuestra;

public interface ObserverZona {

	public void actualizarPorValidacion(ZonaDeCobertura zonaDeCobertura, PostMuestra postMuestra);

	public void actualizarPorCarga(ZonaDeCobertura zonaDeCobertura, PostMuestra posteo);

}
