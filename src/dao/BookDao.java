package dao;

import vo.Book;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class BookDao {


    public void saveData(String path, List<Book> bookList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(bookList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> listAllBooks(String path) {
        ObjectInputStream ois=null;
        try {
          ois=  new ObjectInputStream(new FileInputStream(path));
            List<Book> booklist = (List<Book>) ois.readObject();
          if (booklist.size()!=0){return booklist;}
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
