package epam.homework.task4.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import epam.homework.task4.bean.AddNoteRequest;
import epam.homework.task4.bean.CreateNewNoteBookRequest;
import epam.homework.task4.bean.CurrentFileRequest;
import epam.homework.task4.bean.FindNotesByDateRequest;
import epam.homework.task4.bean.FindNotesRequest;
import epam.homework.task4.bean.LoadFileRequest;
import epam.homework.task4.bean.Request;
import epam.homework.task4.bean.Response;
import epam.homework.task4.bean.SaveNotesRequest;
import epam.homework.task4.controller.Controller;

public class View {

	private static boolean exit = true;
	static Scanner in = new Scanner(System.in);
	private static String help = "Take a list of command:\n0 - EXIT\n\'help\' - HELP\n1 - CREATE NEW NOTEBOOK\n2 - ADD NEW NOTE\n3 - LOAD FILE\n4 - FIND NOTES BY CONTENT\n5 - SAVE\n6 - FIND NOTES BY DATE\n7 - SHOW NOTES TO THE SREEN";

	public static void main(String[] args) {
		Controller controller = new Controller();
		CreateNewNoteBookRequest createNoteBook = new CreateNewNoteBookRequest();
		LoadFileRequest loadFile = new LoadFileRequest();

		System.out.println("WELCOME TO NOTEBOOK SYSTEM! MAKE YOUR COMMAND OR PRINT \'help\' TO CALL HELP AND FIND IT");
		while (exit) {
			System.out.println("Enter the command");
			String choise = in.nextLine();

			// CLOSE PROGRAM
			if (choise.equals("0")) {
				System.out.println("SYSTEM IS CLOSING...\nBEST REGARDS!");
				break;
			}
			switch (choise) {

			// CALL HELP
			case "help":
				System.out.println(help);
				break;

			// CREATE NEW NOTEBOOK
			case "1":

				System.out.println("Create new NOTEBOOK.\nEnter the name of your file.");
				createNoteBook.setCommandName("CREATE_NEW_FILE");
				Response createNoteBookResponse = controller.doRequest(createNoteBook);
				if (createNoteBookResponse.isErrorStatus() == true) {
					System.out.println(createNoteBookResponse.getErrorMessage());
				} else {
					System.out.println(createNoteBookResponse.getResultMessage());
				}
				break;
			// ADD NOTE
			case "2":
				AddNoteRequest addNoteRequest = new AddNoteRequest();
				addNoteRequest.setCommandName("ADD_NEW_NOTE");
				System.out.println("Write you note here: ");
				String note = in.nextLine();
				addNoteRequest.setNote(note);

				Response addNoteResponse = controller.doRequest(addNoteRequest);
				if (addNoteResponse.isErrorStatus() == true) {
					System.out.println(addNoteResponse.getErrorMessage());
				} else {
					System.out.println(addNoteResponse.getResultMessage());
				}
				break;

			// LOAD FILE
			case "3":

				System.out.println("Please wrire the name of file would you like to load, and go on to work with it: ");
				loadFile.setCommandName("LOAD_FILE");
				String loadFileName = in.nextLine() + ".txt";
				loadFile.setLoadFileName(loadFileName);
				Response loadResponse = controller.doRequest(loadFile);
				if (loadResponse.isErrorStatus() == true) {
					System.out.println(loadResponse.getErrorMessage());
				} else {
					System.out.println(loadResponse.getResultMessage());
					CurrentFileRequest.setCurrentFileName(loadFileName);
				}
				break;

			// FIND NOTES BY CONTENT
			case "4":
				List<String> foundNotes = new ArrayList<>();
				FindNotesRequest find = new FindNotesRequest();
				find.setCommandName("FIND_NOTES");
				System.out.println("To Find Notes By Content, pls write your key word for search: ");
				String keyWords = in.nextLine();
				find.setKeyWords(keyWords);
				Response findContent = controller.doRequest(find);
				if (findContent.isErrorStatus() == true) {
					System.out.println(findContent.getErrorMessage());
				} else {
					foundNotes = findContent.getFoundNotes();
					for (String line : foundNotes) {
						System.out.println(line);
					}
					System.out.println(findContent.getResultMessage());
				}
				break;

			// SAVE ALL TO NOTEBOOK
			case "5":
				SaveNotesRequest save = new SaveNotesRequest();
				System.out.println("Now Progam will save all your notes in file. Pls write the name of it: \n");
				String saveFileName = in.nextLine() + ".txt";
				save.setFileName(saveFileName);
				save.setCommandName("SAVE");
				Response saveResponse = controller.doRequest(save);
				if (saveResponse.isErrorStatus() == true) {
					System.out.println(saveResponse.getErrorMessage());
				} else {
					System.out.println(saveResponse.getResultMessage());
				}
				break;
			// FIND NOTES BY DATE
			case "6":

				List<String> foundNotesbyDate = new ArrayList<>();
				FindNotesByDateRequest findByDateRequest = new FindNotesByDateRequest();
				findByDateRequest.setCommandName("FIND_NOTES_BY_DATE");
				System.out
						.println("For searching by date, pls write it in a format yyyy-MM-dd (for example 2000-01-30)");
				String searchDate = in.nextLine();
				findByDateRequest.setSearchDate(searchDate);
				Response findByDate = controller.doRequest(findByDateRequest);
				if (findByDate.isErrorStatus() == true) {
					System.out.println(findByDate.getErrorMessage());

				} else {
					foundNotesbyDate = findByDate.getFoundNotesbyDate();
					for (String line : foundNotesbyDate) {
						System.out.println(line);
					}
					System.out.println(findByDate.getResultMessage());
				}
				break;
			// SHOW NOTES TO THE SREEN
			case "7":

				List<String> notesList = new ArrayList<>();
				Request request = new Request();
				request.setCommandName("SHOW_NOTES_TO_SCREEN");
				Response showResponse = controller.doRequest(request);
				if (showResponse.isErrorStatus() == true) {
					System.out.println(showResponse.getErrorMessage());
				} else {

					notesList = showResponse.getNotesList();
					for (String line : notesList) {
						System.out.println(line);
					}

					System.out.println(showResponse.getResultMessage());
				}

			default:
				System.out
						.println("You write an unknown command. Pls try again or call \'help\' to find your command..");

				break;
			}

		}
	}
}