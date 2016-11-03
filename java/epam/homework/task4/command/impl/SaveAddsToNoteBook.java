package epam.homework.task4.command.impl;

import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.bean.SaveNotesRequest;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class SaveAddsToNoteBook implements Command {

	public Response execute(Request request) throws CommandException {

		SaveNotesRequest req = new SaveNotesRequest();
		Response response = new Response();
		if (request instanceof SaveNotesRequest) {
			req = (SaveNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		String filePath = req.getFileName();
		ServiceFactory service = ServiceFactory.getInstance();
		NoteBookService nbService = service.getNoteBookService();
		try {
			if (nbService.saveNoteBookToFile(filePath)) {
				response.setErrorStatus(false);
				response.setResultMessage("Notes is saved to file: \'" + filePath + "\'");
				return response;
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("OOooops.. I think that you didn't write a file path");
				return response;
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

	}

}
