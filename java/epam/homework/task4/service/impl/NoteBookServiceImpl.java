package epam.homework.task4.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import epam.homework.task4.bean.entity.Note;
import epam.homework.task4.bean.entity.NoteBook;
import epam.homework.task4.date.DateChekFormat;
import epam.homework.task4.source.NoteBookProvider;
import epam.homework.task4.service.NoteBookService;
import epam.homework.task4.service.exception.ServiceException;

public class NoteBookServiceImpl implements NoteBookService {

	public boolean addNote(String note) throws ServiceException {
		// parameters validation
		if (note == null || "".equals(note)) {
			throw new ServiceException("Wrong parameter!");
		}

		Note newNote = new Note(note);

		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();
		noteBook.add(newNote);
		return true;
	}

	@Override
	public boolean createNewNoteBook() {

		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		noteBook.clearNoteBook();

		return true;

	}

	public List<Note> findNotesByContent(String keyWords) throws ServiceException {

		// parameters validation

		if (keyWords == null || "".equals(keyWords)) {

			throw new ServiceException("Wrong parameter!");

		}

		List<Note> foundNotes = new ArrayList<Note>();

		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		foundNotes = noteBook.FindNotesByContent(keyWords);
		if (foundNotes.isEmpty()) {
			throw new ServiceException("There is nothing found by yor request");
		} else {
			return foundNotes;
		}
	}

	@Override

	public List<Note> findNotesByDate(String dateKey) throws ServiceException {

		// parameters validation

		if (!DateChekFormat.isValid(dateKey)) {
			throw new ServiceException(
					"WRONG FORMAT" + dateKey + "\nPlease write date in correct format. \'yyyy-MM-dd\' ");
		}

		List<Note> foundNotes;

		NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

		foundNotes = noteBook.FindNotesByDate(dateKey);
		if (!foundNotes.isEmpty()) {
			return foundNotes;
		} else {
			throw new ServiceException("There is nothing found by yor request");
		}

	}

	@Override

	public boolean loadNoteBookFromFile(String loadFileName) throws ServiceException {

		loadFileName = loadFileName.trim();
		if (loadFileName == null || loadFileName.length() < 1) {
			throw new ServiceException("ERROR! Wrong file name");
		}

		try {

			FileInputStream loadFile = new FileInputStream(loadFileName);

			ObjectInputStream uncoding = new ObjectInputStream(loadFile);

			NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

			while (loadFile.available() > 0) {

				Note currentNote = (Note) uncoding.readObject();
				noteBook.add(currentNote);

			}

			uncoding.close();

			loadFile.close();

			return true;

		} catch (IOException | ClassNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override

	public boolean saveNoteBookToFile(String fileLocation) throws ServiceException {

		fileLocation = fileLocation.trim();

		if (fileLocation == null || fileLocation.length() < 1) {
			throw new ServiceException("Error!!!! Wrong File Name!");
		}

		try {

			FileOutputStream saveFile = new FileOutputStream(fileLocation);

			ObjectOutputStream codingSaveFile = new ObjectOutputStream(saveFile);

			NoteBook noteBook = NoteBookProvider.getInstance().getNoteBook();

			for (Note note : noteBook.getNotes()) {

				codingSaveFile.writeObject(note);

				codingSaveFile.reset();

			}

			codingSaveFile.close();

			saveFile.close();

			return true;
		} catch (IOException e) {

			throw new ServiceException(e.getMessage());

		}

	}

}
