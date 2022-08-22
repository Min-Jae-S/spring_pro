package kr.co.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kr.co.entity.User;

public class MainClass {

	public static void main(String[] args) {
		String pass = "C:\\Users\\User\\Desktop\\test\\objectfile.md";
		User saveUser = new User("user1", "a12345", 30, true);
		
		saveObject(pass, saveUser);
		
		User loadUser = (User) loadObject(pass);
		System.out.println(loadUser);
		
	}
	
	public static void saveObject(String path, User user) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			System.out.println("Write Success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Object loadObject(String path) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			System.out.println("Load Success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}
