package com.lvtaipeng.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * �����ļ�����ȡ��չ��
	 * @param fileName "aa.png"
	 * @return
	 */
	public static String getExtName(String fileName) {
		//������쳣
		if(fileName==null || "".equals(fileName)) {
			throw new RuntimeException("�ļ�������Ϊ��");
		}
		if(fileName.indexOf(".")<=-1) {
			throw new RuntimeException(fileName+":���ļ���û�а�����չ��");
		}
		String extName = fileName.substring(fileName.lastIndexOf("."));
		return extName;
	}
	/**
	 * ��ȡϵͳ��ǰ�û�Ŀ¼
	 * @return
	 */
	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}
	/**
	 * @Title: getSystemTempDirectory   
	 * @Description: ����ϵͳ��ʱĿ¼
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}
	/**
	 * @Title: readTextFileByLine   
	 * @Description: ��ȡ�ļ�����   
	 * @param: @param pathname
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFileByLine(String pathname) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(new File(pathname)));
			do {
				sb.append(br.readLine());
				sb.append("\r\n");
			}while(br.read()!=-1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			StreamUtil.closeAll(br);
		}
		return sb.toString();
	}
	/**
	 * @Title: readTextFileOfList   
	 * @Description: ���ж�ȡ�ļ����ݵ�list����   
	 * @param: @param pathname
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	public static List<String> readTextFileOfList(String pathname) {
		BufferedReader br = null;
		String str = null;
		List<String> strList = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(new File(pathname)));
			/*do {
				strList.add(br.readLine());
			}while(br.read()!=-1);*/
			while((str=br.readLine())!=null) {
				strList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			StreamUtil.closeAll(br);
		}
		return strList;
	}
	/**
	 * @Title: deleteFile   
	 * @Description: �ݹ�ɾ���ļ�   
	 * @param: @param file      
	 * @return: void      
	 * @throws
	 */
	public static void deleteFile(File file) {
		if(file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for(File theFile:listFiles) {
				deleteFile(theFile);
			}
			file.delete();
		}else {
			file.delete();
		}
	}
	/**
	 * @Title: deleteFile   
	 * @Description: �ݹ�ɾ���ļ�  
	 * @param: @param filePath      
	 * @return: void      
	 * @throws
	 */
	public static void deleteFile(String filePath) {
		deleteFile(new File(filePath));
	}
	/**
	 * @Title: getFileSize   
	 * @Description: ����ļ���С
	 * �����ļ���ָ����λ��С��ʾ
	 * File a.txt=2k  
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length/1024.0;
//		return Math.round((length/1024.0))+"kb";
		return String.format("%.2f",len)+"kb";
	}
	
	public static String getFileSize(String fileFullName) {
		return getFileSize(new File(fileFullName));
	}
	
	public static void main(String[] args) {
		System.out.println(getSystemTempDirectory());
	}
}
