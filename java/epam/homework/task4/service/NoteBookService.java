package epam.homework.task4.service;

import java.util.List;

import epam.homework.task4.service.exception.ServiceException;
import epam.homework.task4.bean.entity.Note;;

public interface NoteBookService {

	void addNote(String note) throws ServiceException;

	void createNewNoteBook();

	List<Note> findNotesByContent(String keyWords) throws ServiceException;

	List<Note> findNotesByDate(String dateKey) throws ServiceException;

	void loadNoteBookFromFile(String loadFileName) throws ServiceException;

	void saveNoteBookToFile(String saveFileName) throws ServiceException;

}
