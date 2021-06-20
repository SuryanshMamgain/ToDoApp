package com.suryansh.todo.repo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.suryansh.todo.dto.TodoDTO;

public interface ITodoRepo {
	public boolean addtask(ArrayList<TodoDTO> todo) throws IOException;
	public ArrayList<TodoDTO> printtask() throws IOException, ClassNotFoundException, FileNotFoundException;

}
