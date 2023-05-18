package Usuario;

import java.util.List;

import muestra.Opinion;
import muestra.PostMuestra;

public class HistorialEnApp {
	private List<PostMuestra> posts;
	private List<Opinion> opiniones;
	
	public void addPost(PostMuestra post) {
		this.getPosts().add(post);
	}
	
	public void addOpinion(Opinion op) {
		this.getOpiniones().add(op);
	}

	public List<PostMuestra> getPosts() {
		return posts;
	}

	public void setPosts(List<PostMuestra> posts) {
		this.posts = posts;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
}
