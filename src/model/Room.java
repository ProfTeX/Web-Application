package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Room database table.
 * 
 */
@Entity
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Course")
	private String course;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Lob
	@Column(name="Description")
	private String description;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-many association to Chapter
	@ManyToMany
	@JoinTable(
		name="Room_has_Chapter"
		, joinColumns={
			@JoinColumn(name="Room_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Chapter_ID")
			}
		)
	private List<Chapter> chapters;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="User_has_Room"
		, joinColumns={
			@JoinColumn(name="Room_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="User_ID")
			}
		)
	private List<User> users;

	public Room() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Chapter> getChapters() {
		return this.chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}