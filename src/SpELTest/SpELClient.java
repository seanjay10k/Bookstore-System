package SpELTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpELClient {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext container= new ClassPathXmlApplicationContext("application.xml");
		SpringBeanB spb= container.getBean(SpringBeanB.class);
		System.out.println("\n"+spb.getRandomValue());
		container.close();

	}

}
