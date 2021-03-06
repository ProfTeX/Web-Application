package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="User")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="Email")
	private String email;
	@Column(name="Password")
	private String password;
	
	
	//bi-directional many-to-many association to Room
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name="User_has_Room", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Room_ID")})
	private List<Room> rooms = new ArrayList<Room>();


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

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public Room addRoom(Room room){
		this.rooms.add(room);
		return room;
	}
	public Room removeRoom(Room room){
		this.rooms.remove(room);
		return room;
	}
	
	public User() {
	}
	
	@Override
	public String toString(){
		return "{\"id\":" + this.id + ", \"name\":\"" + this.name + "\", \"email\":\"" + this.email + "\"}";
	}	

	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof User)) return false;
		if (((User) other).getId() == 0) return false;
		if (((User) other).getId() == this.id) return true;
		return false;
	}
}