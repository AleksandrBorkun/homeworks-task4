package epam.homework.task4.command.impl;

import java.io.File;

import epam.homework.task4.bean.LoadFileRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.command.Command;
import epam.homework.task4.command.exception.CommandException;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.ServiceFactory;
import epam.homework.task4.service.exception.ServiceException;

public class LoadNoteBookFile implements Command {

	public Response execute(Request request) throws CommandException {

		LoadFileRequest req = new LoadFileRequest();
		Response response = new Response();

		if (request instanceof LoadFileRequest) {
			req = (LoadFileRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		String fileName = req.getLoadFileName();

		// PATH FOR SEARH FILE
		File loadFile = new File("./");
		String[] find = loadFile.list();
		int count = 0;
		for (String searchName : find) {
			if (searchName.equals(fileName)) {
				ServiceFactory service = ServiceFactory.getInstance();
				NoteBookService nbService = service.getNoteBookService();
				try {
					nbService.loadNoteBookFromFile(fileName);
				} catch (ServiceException e) {
					response.setErrorStatus(true);
					response.setErrorMessage("Ooops.. make sure that you have wrote a file name, and try do it again");
					return response;
				}
				response.setErrorStatus(false);
				response.setResultMessage(fileName + " is load success!");
				count++;
				break;

			}
		}
		if (count == 0) {
			response.setErrorStatus(true);
			response.setErrorMessage(fileName + " does not found!!!");
			return response;

		}

		return response;
	}

}
