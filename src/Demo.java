
import org.joda.time.*;

import controller.PrdEnum;

public class Demo {

	public static void main(String[] args) {
		
		/*Period p1 = Period.minutes(90);
		p1 = p1.plusHours(1);
		p1 = p1.plusDays(10);
		p1 = p1.plusWeeks(6);
		//p1 = p1.plusMonths(1);
		p1 = p1.normalizedStandard();
		//p1 = p1.toStandardDays().toPeriod();
		
		String message = p1.getYears() + " years, ";
		message = message + p1.getMonths() + " months, ";
		message = message + p1.getWeeks() + " weeks, ";
		message = message + p1.getDays() + " days, ";
		message = message + p1.getHours() + " hours, ";
		message = message + p1.getMinutes() + " minutes, ";
		System.out.println(message);
		System.out.println("But, total days is "+p1.toStandardDays().toString());*/
		
		String regex = "for (";
		for(PrdEnum prd : PrdEnum.values()){
			regex = regex + prd.regex() + ")?(, | and )?(";
		}
		regex = regex + ")?";
		System.out.println(regex);
	}
}
