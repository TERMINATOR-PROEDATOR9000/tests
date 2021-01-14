package jr;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		printDate("21.4.2014 15:56:45");
		System.out.println();
		printDate("21.4.2014");
		System.out.println();
		printDate("17:33:40");
	}

	public static void printDate(String date) {
		// напишите тут ваш код
		SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date d;
		Calendar c = Calendar.getInstance();
		try {
			d = s.parse(date);
			c.setTime(d);
			System.out.printf(
					"День: %d\n"
							+ "День недели: %d\n"
							+ "День месяца: %d\n"
							+ "День года: %d\n"
							+ "Неделя месяца: %d\n"
							+ "Неделя года: %d\n"
							+ "Месяц: %d\n"
							+ "Год: %d\n"
							+ "AM или PM: %s\n"
							+ "Часы: %d\n"
							+ "Часы дня: %d\n"
							+ "Минуты: %d\n"
							+ "Секунды %d\n",
					c.get(Calendar.DATE),
					(c.get(Calendar.DAY_OF_WEEK))==Calendar.SUNDAY?7:c.get(Calendar.DAY_OF_WEEK)-1,
					c.get(Calendar.DAY_OF_MONTH),
					c.get(Calendar.DAY_OF_YEAR),
					c.get(Calendar.WEEK_OF_MONTH),
					c.get(Calendar.WEEK_OF_YEAR),
					(c.get(Calendar.MONTH)) + 1,
					c.get(Calendar.YEAR),
					(c.get(Calendar.AM_PM) == Calendar.AM) ? "AM" : "PM",
					c.get(Calendar.HOUR),
					c.get(Calendar.HOUR_OF_DAY),
					c.get(Calendar.MINUTE),
					c.get(Calendar.SECOND));
		} catch (ParseException e) {
			s = new SimpleDateFormat("dd.MM.yyyy");
			try {
				d = s.parse(date);
				c.setTime(d);
				System.out.printf(
						"День: %d\n"
								+ "День недели: %d\n"
								+ "День месяца: %d\n"
								+ "День года: %d\n"
								+ "Неделя месяца: %d\n"
								+ "Неделя года: %d\n"
								+ "Месяц: %d\n"
								+ "Год: %d\n",
						c.get(Calendar.DATE),
						c.get(Calendar.DAY_OF_WEEK),
						c.get(Calendar.DAY_OF_MONTH),
						c.get(Calendar.DAY_OF_YEAR),
						c.get(Calendar.WEEK_OF_MONTH),
						c.get(Calendar.WEEK_OF_YEAR),
						(c.get(Calendar.MONTH)) + 1,
						c.get(Calendar.YEAR));
			} catch (ParseException e1) {
				s = new SimpleDateFormat("HH:mm:ss");
				try {
					d = s.parse(date);
					c.setTime(d);
					System.out.printf(
							"AM или PM: %s\n"
									+ "Часы: %d\n"
									+ "Часы дня: %d\n" 
									+ "Минуты: %d\n" 
									+ "Секунды: %d\n",
							(c.get(Calendar.AM_PM) == Calendar.AM) ? "AM" : "PM",
							c.get(Calendar.HOUR),
							c.get(Calendar.HOUR_OF_DAY),
							c.get(Calendar.MINUTE),
							c.get(Calendar.SECOND));
				} catch (ParseException e2) {
					System.out.println("Wrong input data " + date);
				}
			}
		}
	}
}
