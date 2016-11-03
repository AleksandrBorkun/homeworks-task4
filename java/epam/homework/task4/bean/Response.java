package epam.homework.task4.bean;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private boolean errorStatus;

	private String errorMessage;
	private String resultMessage;
	List<String> foundNotes = new ArrayList<>();
	List<String> foundNotesbyDate = new ArrayList<>();
	List<String> notesList = new ArrayList<>();

	public List<String> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<String> notesList) {
		this.notesList = notesList;
	}

	public List<String> getFoundNotesbyDate() {
		return foundNotesbyDate;
	}

	public void setFoundNotesbyDate(List<String> foundNotesbyDate) {
		this.foundNotesbyDate = foundNotesbyDate;
	}

	public List<String> getFoundNotes() {
		return foundNotes;
	}

	public void setFoundNotes(List<String> foundNotes) {
		this.foundNotes = foundNotes;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
