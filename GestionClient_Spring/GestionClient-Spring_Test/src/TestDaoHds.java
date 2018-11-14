
import java.sql.SQLException;

import org.junit.Test;

import com.noelmace.gestionclient.spring.business.dao.DAO;
import com.noelmace.gestionclient.spring.business.entities.*;


public class TestDaoHds extends AbstractTest {
	
	@Test
	public void testCreate() throws SQLException {
		@SuppressWarnings("unchecked")
		DAO<Client> daoClient = (DAO<Client>) context.getBean("daoClientHds");
		daoClient.create(new Client("Antoine", "Pierre"));
	}

}
