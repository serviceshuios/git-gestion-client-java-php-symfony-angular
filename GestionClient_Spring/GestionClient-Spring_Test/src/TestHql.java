import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;


public class TestHql extends AbstractTest {
	
	@Test
	public void testMachin(){
		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		Session session = sf.openSession();
		session.beginTransaction();
		List result = null;
		try {
			result = session.createQuery("from BonDeCommande bdc left join fetch bdc.client c where c.id = 9").list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(result);

		session.close();
	}

}
