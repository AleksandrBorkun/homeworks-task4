package epam.homework.task4.command.impl;

import java.util.List;

import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.bean.entity.Note;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.source.NoteBookProvider;

public class ShowNotesToScreen implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();

		List<Note> notes = NoteBookProvider.getInstance().getNoteBook().getNotes();
		if (notes.isEmpty() != true) {
			response.setErrorStatus(false);
			response.setResultMessage("The notes is shown great!");
			for (Note added : notes) {
				System.out.println(added.getNote());
			}
		} else {
			response.setErrorStatus(true);
			response.setErrorMessage("You didn\'t wrote any notes yet");
		}

		return response;
	}

}
