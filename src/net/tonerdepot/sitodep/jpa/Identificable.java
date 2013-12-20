package net.tonerdepot.sitodep.jpa;

import javax.persistence.*;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

@MappedSuperclass
public class Identificable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	@Hidden
	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}