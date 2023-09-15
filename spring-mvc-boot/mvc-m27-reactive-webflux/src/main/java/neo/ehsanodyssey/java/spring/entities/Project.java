package neo.ehsanodyssey.java.spring.entities;

public class Project {

	private String name;
	private int estimatedHours;

	public Project(String name, int estimatedHours) {
		super();
		this.name = name;
		this.estimatedHours = estimatedHours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", estimatedHours=" + estimatedHours + "]";
	}

}
