package epam.homework.task4.command.impl;

import java.util.ArrayList;
import java.util.List;

import epam.homework.task4.bean.FindNotesByDateRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.bean.entity.Note;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class FindNotesByDate implements Command {

	public Response execute(Request request) throws CommandException {

		FindNotesByDateRequest req = null;
		Response response = new Response();

		if (request instanceof FindNotesByDateRequest) {
			req = (FindNotesByDateRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String dateKey = req.getSearchDate();

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		List<String> foundNotesbyDate = new ArrayList<>();

		try {
			for (Note search : nbService.findNotesByDate(dateKey)) {
				foundNotesbyDate.add(search.getNote());
			}
			response.setFoundNotesbyDate(foundNotesbyDate);
			response.setErrorStatus(false);
			response.setResultMessage("\nSearch by date complete success\n");
			return response;
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;

		}

	}

}
