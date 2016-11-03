package epam.homework.task4.command.impl;

import epam.homework.task4.bean.AddNoteRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class AddNewNote implements Command {

	public Response execute(Request request) throws CommandException {

		AddNoteRequest req = new AddNoteRequest();
		Response response = new Response();

		if (request instanceof AddNoteRequest) {
			req = (AddNoteRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String note = req.getNote();

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();

		try {
			if (nbService.addNote(note)) {
				response.setErrorStatus(false);
				response.setResultMessage("All OK!");
				return response;
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("oooops something was wrong");
				return response;
			}

		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

	}

}
