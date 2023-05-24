package usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import muestra.Revision;

public class HistorialEnApp {
	private Queue<LocalDate> posts;
	private Queue<Revision> opiniones;
	
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
	
	public Queue<LocalDate> getPosts() {
		return this.posts;
	}

	public void setPosts(Queue<LocalDate> posts) {
		this.posts = posts;
	}

	public Queue<Revision> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(Queue<Revision> opiniones) {
		this.opiniones = opiniones;
	}
}
