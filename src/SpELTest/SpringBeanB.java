package SpELTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanB {

	@Value(" #{simpleBeanA.getFirstValue()}")
	private String secondValue;
	@Value("#{(T(java.lang.Math).random()*10)+1}")
	private double randomValue;

	public String getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
	public double getRandomValue() {
		return (int)randomValue;
	}
	public void setRandomValue(double randomValue) {
		this.randomValue = randomValue;
	}






}
