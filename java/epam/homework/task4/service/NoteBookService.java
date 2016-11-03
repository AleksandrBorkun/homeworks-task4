package epam.homework.task4.service;

import java.util.List;

import epam.homework.task4.service.exception.ServiceException;
import epam.homework.task4.bean.entity.Note;;

public interface NoteBookService {

	boolean addNote(String note) throws ServiceException;

	boolean createNewNoteBook();

	List<Note> findNotesByContent(String keyWords) throws ServiceException;

	List<Note> findNotesByDate(String dateKey) throws ServiceException;

	boolean loadNoteBookFromFile(String loadFileName) throws ServiceException;

	boolean saveNoteBookToFile(String saveFileName) throws ServiceException;

}
