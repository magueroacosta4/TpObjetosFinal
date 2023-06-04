package muestra;

import java.util.List;

public class Ubicacion {

	private final float radioDeLaTierraEnKm = 6378.0F;
	private float longitud;
	private float latitud;
	
	public Ubicacion(float latitud, float longitud) {
		setLongitud(longitud);
		setLatitud(latitud);
	}
	
	private void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	
	private void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	public float getLatitud() {
		return latitud;
	}
	
	public float getLongitud() {
		return longitud;
	}
	
	/**
	 * 
	 * @param epicentro
	 * @return distancia a la ubicacion en KMs
	 */
	public float distanciaA(Ubicacion otraUbicacion) {
		double difLatitud = Math.toRadians(getLatitud() - otraUbicacion.getLatitud());
		double difLongitud = Math.toRadians(getLongitud() - otraUbicacion.getLongitud());
		
		double a = Math.pow(Math.sin(difLatitud / 2), 2) +
		        Math.cos(Math.toRadians(getLatitud())) *
		        Math.cos(Math.toRadians(otraUbicacion.getLatitud())) *
		        Math.pow(Math.sin(difLongitud / 2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (float) (radioDeLaTierraEnKm * c);
	}

	public List<Ubicacion> lasQueEstanAMenosDeNKM(List<Ubicacion> ubicaciones, int distanciaEnKM) {
		return ubicaciones.stream().filter(u -> distanciaA(u) <= distanciaEnKM).toList();
	}

}
