package model;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Chapter")
public class Chapter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Column(name="Position")
	private int position;
	
	//@Column(name="Room_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="Room_ID")
	private Room room;
		
	//bi-directional association to Snippet
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name="Chapter_has_Snippet", joinColumns={@JoinColumn(name="Chapter_ID")}, inverseJoinColumns={@JoinColumn(name="Snippet_ID")})
	private List<Snippet> snippets = new ArrayList<Snippet>();


	public int getId() {
		return this.id;
	}
	void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Room getRoom() {
		return this.room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public int getPosition() {
		return this.position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public List<Snippet> getSnippets() {
		return this.snippets;
	}
	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
	
	public Snippet addSnippet(Snippet snippet) {
		this.snippets.add(snippet);
		return snippet;
	}
	public Snippet removeSnippet(Snippet snippet) {
		this.snippets.remove(snippet);
		return snippet;
	}	

	public Chapter() {
	}
	
	@Override
	public String toString(){
		return "{\"id\":" + this.id + ", \"name\":\"" + this.name + "\", \"position\":" + this.position + ", \"snippets\":" + this.snippets + ", \"room\":" + this.room.getId() + "}";
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Chapter)) return false;
		if (((Chapter) other).getId() == 0) return false;
		if (((Chapter) other).getId() == this.id) return true;
		return false;
	}
}