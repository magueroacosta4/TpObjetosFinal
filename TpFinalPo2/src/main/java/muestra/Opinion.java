package muestra;

public enum Opinion {
	VINCHUCA_INFESTANTS () {
		@Override
		public boolean esVinchuca() {
			return true;
		}
	},
	VINCHUCA_SORDIDA () {
		@Override
		public boolean esVinchuca() {
			return true;
		}
	},
	VINCHUCA_GUASAYANA () {
		@Override
		public boolean esVinchuca() {
			return true;
		}
	},
	CHINCHE_FOLIADA,
	PHTIA_CHINCHE,
	IMAGEN_POCO_CLARA,
	NINGUNA;

	public boolean esVinchuca() {
		return false;
	}
}
