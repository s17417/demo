package base.Utils;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;

public class IdGenerator extends IdentityGenerator {

	
	public static UUID getId() {
		
		return UUID.randomUUID();
	}

	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        //Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        //return id != null ? id : super.generate(session, object);
		return getId().toString();
    }

}
