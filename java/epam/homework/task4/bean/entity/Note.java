package epam.homework.task4.bean.entity;

import java.io.Serializable;

import epam.homework.task4.date.FullDate;

public class Note implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	FullDate date = new FullDate();
	private String note;

	public Note(String note) {

		this.note = note;

	}

	public String getNote() {
		return date.getFullDate() + ": " + note;
	}
}
