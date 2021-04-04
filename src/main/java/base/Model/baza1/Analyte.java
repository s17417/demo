package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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

	public Set<Method> getMethods() {
		return methods;
	}

	protected void setMethods(Set<Method> methods) {
		this.methods.clear();
		this.methods.addAll(methods);
	}
	
	public Method addToLaboratoryTest(LaboratoryTest laboratoryTest) {
		return new Method(this, laboratoryTest);
	}

}
