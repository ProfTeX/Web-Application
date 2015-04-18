package model;
import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="chapter")
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
	@Column(name="Room_ID")
	private int roomId;
	
	
	//bi-directional association to Room
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="Room_ID", insertable=false, updatable=false)
	//private Room room;
	
	//bi-directional association to Snippet
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="chapter_has_snippet", joinColumns={@JoinColumn(name="Chapter_ID")}, inverseJoinColumns={@JoinColumn(name="Snippet_ID")})
	private List<Snippet> snippets = new ArrayList<Snippet>();


	public int getId() {
		return this.id;
	}
	public void setId(int id) {
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
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getRoomId() {
		return this.roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	/*public Room getRoom() {
		return this.room;
	}
	public void setRoom(Room room) {
		if (room.getId() == 0) System.out.println("RaumId ist nicht gesetzt.");
		this.roomId = room.getId();
		this.room = room;
	}*/

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
}