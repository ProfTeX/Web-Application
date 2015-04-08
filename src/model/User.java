package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="Email")
	private String email;

	@Column(name="Name")
	private String name;

	@Column(name="Password")
	private String password;

	//bi-directional many-to-many association to Room
	@ManyToMany(mappedBy="users1")
	private List<Room> rooms1;

	//bi-directional many-to-many association to Room
	@ManyToMany
	@JoinTable(
		name="User_has_Room"
		, joinColumns={
			@JoinColumn(name="User_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Room_ID")
			}
		)
	private List<Room> rooms2;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Room> getRooms1() {
		return this.rooms1;
	}

	public void setRooms1(List<Room> rooms1) {
		this.rooms1 = rooms1;
	}

	public List<Room> getRooms2() {
		return this.rooms2;
	}

	public void setRooms2(List<Room> rooms2) {
		this.rooms2 = rooms2;
	}

}