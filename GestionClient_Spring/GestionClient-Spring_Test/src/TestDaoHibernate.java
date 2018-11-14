import java.sql.SQLException;

import org.junit.Test;

import com.noelmace.gestionclient.spring.business.dao.DAO;
import com.noelmace.gestionclient.spring.business.entities.BonDeCommande;
import com.noelmace.gestionclient.spring.business.entities.Client;


public class TestDaoHibernate extends AbstractTest {

	@Test
	public void testBdcFindAll() throws SQLException {
		DAO<BonDeCommande> daoBdc = (DAO<BonDeCommande>) context.getBean("daoBdcHibernate");
		DAO<Client> daoClient = (DAO<Client>) context.getBean("daoClientHibernate");
		Client client = new Client("toto", "tutu");
		daoClient.create(client);
		daoBdc.create(new BonDeCommande("ref1234", 102.42, client));
		System.out.println(daoBdc.findAll());
		System.out.println(daoBdc.findAll());
		System.out.println(daoBdc.findAll());
	}

}
