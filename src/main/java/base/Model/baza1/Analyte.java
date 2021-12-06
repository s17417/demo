package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractActiveObject;

@Audited
@Entity
@SQLDelete(sql = "UPDATE ANALYTE SET ISACTIVE = false WHERE id = ?")
public class Analyte extends AbstractActiveObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(length=180)
	@Size(min=2, max=180)
	private String name;
	
	@NotBlank
	@Column(length=30, unique = true)
	@Size(min=2, max=30)
	private String shortName;
	
	@Column(length=255)
	@Size(min=2, max=255)
	private String description;
	
	@OneToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
					},
			fetch = FetchType.LAZY,
			mappedBy = "analyte"
			)
	private Set<Method> methods = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Method> getMethods() {
		return methods;
	}

	protected void setMethods(Set<Method> methods) {
		this.methods.clear();
		this.methods.addAll(methods);
	}
	
	public <T extends Method> T addToLaboratoryTest(LaboratoryTest laboratoryTest, T method) {
		method.setLaboratoryTest(laboratoryTest);
		method.setAnalyte(this);
		return method;
	}

}
