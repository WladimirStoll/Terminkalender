package de.stoll.wladimir.terminkalender.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import de.stoll.wladimir.terminkalender.view.IMainView;

public class MainModel {
	
	int selectedWeekOfYear;
	IMainView iMainView;

	public MainModel(IMainView iMainView) {
		super();
		this.iMainView = iMainView;
	}

	public void setSelectedWeekOfYear(int weekOfYear) {
		GregorianCalendar calendar = new GregorianCalendar(Locale.GERMAN);
        int CURRENT_YEAR = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR,CURRENT_YEAR);
        calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        System.out.println("1.Wochentag=" + calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        System.out.println("7.Wochentag=" + calendar.getTime());
        selectedWeekOfYear = 2;
	}

	public int getSelectedWeekOfYear() {
		return selectedWeekOfYear;
	}
}
