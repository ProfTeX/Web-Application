package model;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Room")
public class Room implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="Course")
	private String course;
	@Lob
	@Column(name="Description")
	private String description;
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@OneToMany(mappedBy="room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	private List<Chapter> chapters = new ArrayList<Chapter>();
	
	//bi-directional many-to-many association to User
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name="User_has_Room", joinColumns={@JoinColumn(name="Room_ID")}, inverseJoinColumns={@JoinColumn(name="User_ID")})
	private List<User> users = new ArrayList<User>();
	
	
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
	
	public String getCourse() {
		return this.course;
	}
	public void setCourse(String course) {
		this.course = course;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<Chapter> getChapters(){
		return this.chapters;
	}
	public void setChapters(List<Chapter> chaperts){
		this.chapters = chaperts;
	}
	
	public Chapter addChapter(Chapter chapter){
		this.chapters.add(chapter);
		return chapter;
	}
	public Chapter removeChapter(Chapter chapter){
		this.chapters.remove(chapter);
		return chapter;
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User addUser(User user){
		this.users.add(user);
		return user;
	}
	public User removeUser(User user){
		this.users.remove(user);
		return user;
	}
	
	public Room() {
	}
	
	@Override
	public String toString(){
		return "[{\"id\":" + this.id + ", \"name\":\"" + this.name + "\", \"course\":\"" 
				+ this.course + "\", \"description\":\"" + this.description + "\", \"chapters\":" 
				+ this.chapters + ", \"users\":" + this.users + "}]";
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Room)) return false;
		if (((Room) other).getId() == 0) return false;
		if (((Room) other).getId() == this.id) return true;
		return false;
	}
}