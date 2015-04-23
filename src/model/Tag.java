package model;
import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Tag")
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Name")
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	
	//bi-directional many-to-many association to Snippet
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//@Fetch(FetchMode.SELECT)
	//@JoinTable(name="Snippet_has_Tag", joinColumns={@JoinColumn(name="Tag_ID")}, inverseJoinColumns={@JoinColumn(name="Snippet_ID")})
	@ManyToMany(mappedBy = "tags")
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

	public List<Snippet> getSnippets() {
		return this.snippets;
	}
	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
	
	public Snippet addSnippet(Snippet snippet){
		this.snippets.add(snippet);
		return snippet;
	}
	public Snippet removeSnippet(Snippet snippet){
		this.snippets.remove(snippet);
		return snippet;
	}
	
	public Tag() {
	}
	
	@Override
	public String toString(){
		return "{\"id\":" + this.id + ", \"name\":\"" + this.name + "\"}";
	}
}