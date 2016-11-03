package epam.homework.task4.command.impl;

import java.util.ArrayList;
import java.util.List;

import epam.homework.task4.bean.FindNotesRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.bean.entity.Note;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class FindNotes implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		FindNotesRequest req = new FindNotesRequest();
		if (request instanceof FindNotesRequest) {
			req = (FindNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		List<String> foundNotes = new ArrayList<>();
		String keyWord = req.getKeyWords();
		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		try {
			for (Note found : nbService.findNotesByContent(keyWord)) {
				foundNotes.add(found.getNote());
			}
			response.setFoundNotes(foundNotes);
			response.setErrorStatus(false);
			response.setResultMessage("\nSearch completed success\n");
			return response;
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
	}

}
