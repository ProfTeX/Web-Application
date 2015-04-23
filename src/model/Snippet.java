package model;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Snippet")
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
	@Column(name="Position")
	private int position;
	
	
	//bi-directional many-to-many association to Chapter
	//, joinColumns={@JoinColumn(name="Snippet_ID")}, inverseJoinColumns={@JoinColumn(name="Chapter_ID")}
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name="Chapter_has_Snippet", joinColumns={@JoinColumn(name="Snippet_ID")}, inverseJoinColumns={@JoinColumn(name="Chapter_ID")})
	private List<Chapter> chapters = new ArrayList<Chapter>();
	
	
	//bi-directional many-to-many association to Tag
	@ManyToMany(cascade=CascadeType.DETACH)
	@Fetch(FetchMode.SELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="Snippet_has_Tag", joinColumns={@JoinColumn(name="Snippet_ID")}, inverseJoinColumns={@JoinColumn(name="Tag_ID")})
	private List<Tag> tags = new ArrayList<Tag>();

	
	public int getId() {
		return this.id;
	}
	void setId(int id) {
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
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public int getPosition() {
		return this.position;
	}
	public void setPosition(int position) {
		this.position = position;
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
	
	public List<Tag> getTags() {
		return this.tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public Tag addTag(Tag tag){
		this.tags.add(tag);
		return tag;
	}
	public Tag removeTag(Tag tag){
		this.tags.remove(tag);
		return tag;
	}
	
	public Snippet() {
	}
	
	@Override
	public String toString(){
		ListToString<Tag> lts = new ListToString<Tag>();
		return "{\"id\":" + this.id + ", \"title\":\"" + this.title + "\", \"content\":\"" + this.content + "\", \"tags\":" + lts.listToString(this.tags) + ", \"position\":" + this.position + "}";
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Snippet)) return false;
		if (((Snippet) other).getId() == 0) return false;
		if (((Snippet) other).getId() == this.id) return true;
		return false;
	}
}