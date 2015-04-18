package model;
import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="snippet")
public class Snippet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Title")
	private String title;
	@Lob
	@Column(name="Content")
	private String content;
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	
	//bi-directional many-to-many association to Chapter
	//, joinColumns={@JoinColumn(name="Snippet_ID")}, inverseJoinColumns={@JoinColumn(name="Chapter_ID")}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="chapter_has_snippet", joinColumns={@JoinColumn(name="Snippet_ID")}, inverseJoinColumns={@JoinColumn(name="Chapter_ID")})
	private List<Chapter> chapters = new ArrayList<Chapter>();
	
	
	/*//bi-directional many-to-many association to Tag
	@ManyToMany(mappedBy="snippets")
	private List<Tag> tags;*/

	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public Chapter addChapter(Chapter chapter) {
		this.chapters.add(chapter);
		return chapter;
	}
	public Chapter removeChapter(Chapter chapter) {
		this.chapters.remove(chapter);
		return chapter;
	}	
	
	/*public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}*/
	
	public Snippet() {
	}
}