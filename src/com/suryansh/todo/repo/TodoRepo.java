package com.suryansh.todo.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.suryansh.todo.dto.TodoDTO;
import com.suryansh.todo.utils.Constants;


public class TodoRepo implements ITodoRepo{
	File file;
	private static TodoRepo todorepo=null;
	private TodoRepo() {
		// TODO Auto-generated constructor stub
		file=new File(Constants.PATH);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static TodoRepo getInstance() {
		if(todorepo==null) {
			todorepo=new TodoRepo();
		}
		return todorepo;
	}

	@Override
	public boolean addtask(ArrayList<TodoDTO> tasks) throws IOException {
		FileOutputStream fo=null;
		ObjectOutputStream os=null;
		try {
		fo=new FileOutputStream(file);
		os=new ObjectOutputStream(fo);
		os.writeObject(tasks);
		}
		finally {
			if(os!=null) {
				os.close();
			}
			if(fo!=null) {
				fo.close();
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArrayList<TodoDTO> printtask() throws IOException,ClassNotFoundException,FileNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<TodoDTO> list=new ArrayList<>();
		try(FileInputStream fs=new FileInputStream(file)){
			try(ObjectInputStream oi=new ObjectInputStream(fs)){
				list=(ArrayList<TodoDTO>)oi.readObject();
			}
		}
		return list;
	}
	
}
