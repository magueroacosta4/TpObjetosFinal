package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestra.Revision;

public class HistorialEnApp {
	private List<LocalDate> posts;
	private List<Revision> opiniones;
	
	
	public HistorialEnApp() {
		this.setPosts(new ArrayList<LocalDate>());
		this.setOpiniones(new ArrayList<Revision>());
	}
	
	public void addPost() {
		this.getPosts().add(LocalDate.now());
	}
	
	public void addOpinion(Revision op) {
		this.getOpiniones().add(op);
	}
	
	public int getCantPosts30Dias(){
		List<LocalDate> cant;
		LocalDate hoy = LocalDate.now();
		cant = this.getPosts().
				stream().
				filter(p -> p.isAfter(hoy.minusDays(30))).collect(Collectors.toList());
		return cant.size();
	}
	
	public int getCantOpiniones30Dias(){
		List<Revision> cant;
		LocalDate hoy = LocalDate.now();
		cant = this.getOpiniones().
				stream().
				filter(p -> p.getFechaDeCreacion().isAfter(hoy.minusDays(30))).
				collect(Collectors.toList());
		return cant.size();
	}
	
	public List<LocalDate> getPosts() {
		return this.posts;
	}

	public void setPosts(List<LocalDate> posts) {
		this.posts = posts;
	}

	public List<Revision> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Revision> opiniones) {
		this.opiniones = opiniones;
	}
}
