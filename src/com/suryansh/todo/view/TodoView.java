package com.suryansh.todo.view;
import static com.suryansh.todo.utils.MessageReader.getValue;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.suryansh.todo.dto.TodoDTO;
import com.suryansh.todo.repo.ITodoRepo;
import com.suryansh.todo.repo.TodoRepo;
import com.suryansh.todo.utils.Constants;

import static com.suryansh.todo.utils.Constants.*;

public class TodoView {
	private static Scanner scanner=new Scanner(System.in);
	private static void addTask() {
		//scanner.nextLine();
		System.out.println(getValue("input.taskname"));
		String name=scanner.nextLine();
		System.out.println(getValue("input.taskdesc"));
		String desc=scanner.nextLine();
		TodoDTO todo=new TodoDTO(name,desc);

		ITodoRepo repo=TodoRepo.getInstance();
		
		ArrayList<TodoDTO> tasks=null;
		String result="";
		try {
			try {
			tasks=repo.printtask();
			}catch (EOFException e) {
				System.out.println("No data found in file");
			}
			if(tasks!=null && tasks.size()>0) {
				tasks.add(todo);
			}
			else {
				tasks=new ArrayList<>();
				tasks.add(todo);
			}
			result = repo.addtask(tasks)?getValue("record.added"):getValue("record.notadded");
		} catch (IOException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(result);
	
	}
	private static void deleteTask() {
//		scanner.nextLine();
		System.out.println(getValue("delete.taskname"));
		String deletetask=scanner.nextLine();
		int flag=0;
		TodoDTO deleteTaskDTO=null;
		
		try {
			ITodoRepo repo=TodoRepo.getInstance();
			
			ArrayList<TodoDTO> tasks=repo.printtask();
			for(TodoDTO task:tasks)
			{
				if(task.getName().equals(deletetask))
				{
					flag=1;
					deleteTaskDTO=task;
					break;
				}
			}
			if(flag==1)
			{
				tasks.remove(deleteTaskDTO);	
				repo.addtask(tasks);
				System.out.println(getValue("delete.success"));
			}
			else
			{
				System.out.println(getValue("delete.notfound"));
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void updateTask()
	{
//		scanner.nextLine();
		System.out.println(getValue("update.taskname"));
		String updateTaskName = scanner.nextLine();
		TodoDTO updateTaskDTO = null;
		
		int flag = 0;
		
		ITodoRepo repo;
		try {
			repo = TodoRepo.getInstance();
			ArrayList<TodoDTO> tasks = repo.printtask();
			
			for(TodoDTO task:tasks)
			{
				if(task.getName().equals(updateTaskName))
				{
					flag = 1;
					updateTaskDTO = task;
					break;
				}
			}
			
			if(flag==1)
			{
				updateTaskDTO.setStatus(Constants.COMPLETE);
				repo.addtask(tasks);
				System.out.println(getValue("update.success"));
			}
			else
			{
				System.out.println(getValue("update.fail"));
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
	
	private static void searchTask()
	{
//		scanner.nextLine();
		System.out.println(getValue("search.task"));
		String taskname=scanner.nextLine();
		int flag=0;
		try {
			ITodoRepo repo = TodoRepo.getInstance();
			ArrayList<TodoDTO> tasks=repo.printtask();
			for(TodoDTO task:tasks)
			{
				if(task.getName().equals(taskname))
				{
					flag=1;
					System.out.println(task);
					break;
				}
			}
			if(flag==0)
			{
				System.out.println(getValue("search.fail"));
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void printAlltasks() {
		ITodoRepo repo=TodoRepo.getInstance();
		try {
			ArrayList<TodoDTO> tasks=repo.printtask();
			for(TodoDTO task:tasks) {
				System.out.println(task);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		outer:
		while(true) {
			System.out.println(getValue("addtask"));
			System.out.println(getValue("deletetask"));
			System.out.println(getValue("updatetask"));
			System.out.println(getValue("searchtask"));
			System.out.println(getValue("printtask"));
			System.out.println(getValue("exittask"));
			System.out.println(getValue("choice"));
			int choice=scanner.nextInt();
			switch(choice) {
			case ADD_TASK:
				addTask();
				break;
			case DELETE_TASK:
				deleteTask();
				break;
			case UPDATE_TASK:
				updateTask();
				break;
			case SEARCH_TASK:
				searchTask();
				break;
			case PRINT_TASK:
				printAlltasks();
				break;
			case EXIT_TASK:
				break outer;
			}
		}
		scanner.close();
	}

}
