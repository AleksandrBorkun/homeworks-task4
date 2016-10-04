package epam.homework.task4.controller;

import java.util.HashMap;
import java.util.Map;

import epam.homework.task4.command.Command;
import epam.homework.task4.command.impl.AddNewNote;
import epam.homework.task4.command.impl.CreateNewNoteBook;
import epam.homework.task4.command.impl.FindNotes;
import epam.homework.task4.command.impl.FindNotesByDate;
import epam.homework.task4.command.impl.LoadNoteBookFile;
import epam.homework.task4.command.impl.SaveAddsToNoteBook;
import epam.homework.task4.command.impl.ShowNotesToScreen;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {

		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("FIND_NOTES", new FindNotes());
		commands.put("CREATE_NEW_FILE", new CreateNewNoteBook());
		commands.put("LOAD_FILE", new LoadNoteBookFile());
		commands.put("SAVE", new SaveAddsToNoteBook());
		commands.put("FIND_NOTES_BY_DATE", new FindNotesByDate());
		commands.put("SHOW_NOTES_TO_SCREEN", new ShowNotesToScreen());

	}

	public Command getCommand(String commandName) {
		Command command;

		command = commands.get(commandName);

		return command;

	}

}
