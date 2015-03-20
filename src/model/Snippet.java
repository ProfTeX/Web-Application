package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Snippet database table.
 * 
 */
@Entity
@NamedQuery(name="Snippet.findAll", query="SELECT s FROM Snippet s")
public class Snippet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Lob
	@Column(name="Content")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="Title")
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-many association to Chapter
	@ManyToMany
	@JoinTable(
		name="Chapter_has_Snippet"
		, joinColumns={
			@JoinColumn(name="Snippet_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Chapter_ID")
			}
		)
	private List<Chapter> chapters;

	//bi-directional many-to-many association to Tag
	@ManyToMany(mappedBy="snippets")
	private List<Tag> tags;

	public Snippet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Chapter> getChapters() {
		return this.chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}