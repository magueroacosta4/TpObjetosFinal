package usuario;

import java.time.LocalDate;
import java.util.Queue;

import muestra.Revision;

public class HistorialEnApp {
	private Queue<LocalDate> posts;
	private Queue<Revision> opiniones;
	
	public void addPost(LocalDate post) {
		this.getPosts().add(post);
	}
	
	public void addOpinion(Revision op) {
		this.getOpiniones().add(op);
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
