package epam.homework.task4.command.impl;

import epam.homework.task4.bean.CreateNewNoteBookRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class CreateNewNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		Response response = new Response();
		CreateNewNoteBookRequest req;
		
		if (request instanceof CreateNewNoteBookRequest) {
			req = (CreateNewNoteBookRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();
		nbService.createNewNoteBook();
		try {
			nbService.addNote("NoteBook is created");
			response.setErrorStatus(false);
			response.setResultMessage("NoteBook created success!");
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("WooW!!! Somethings go wrong! Pls try again later...");
			return response;
		}
		
		return response;
	}

}
