
import java.sql.SQLException;

import org.junit.Test;

import com.noelmace.gestionclient.spring.business.services.ClientService;


public class TestServices extends AbstractTest {

	private ClientService clientService;

	@Test
	public void testCreateClient() throws SQLException {
		clientService = (ClientService) context.getBean("clientService");
		clientService.create("Machin", "Truc441");
	}

}
