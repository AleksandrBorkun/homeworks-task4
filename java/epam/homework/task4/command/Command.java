package epam.homework.task4.command;

import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.command.exception.CommandException;

public interface Command {
	
	Response execute(Request request) throws CommandException;
	
	
}
