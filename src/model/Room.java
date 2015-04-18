package model;
import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="room")
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
	
	
	//bi-directional many-to-many association to User
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_has_room", joinColumns={@JoinColumn(name="Room_ID")}, inverseJoinColumns={@JoinColumn(name="User_ID")})
	private List<User> users = new ArrayList<User>();
	
	
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
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
}