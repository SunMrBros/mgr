package com.my.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static Logger logger=Logger.getLogger(FileUtil.class.getName());
	/**
	 * 保存文件到路径, 并返回数据库保存路径
	 * @param file
	 * @param dir
	 * @param session
	 * @return
	 */
	public static String saveFile(MultipartFile file1,String dir,HttpServletRequest request){
		HttpSession session=request.getSession();
		String filename=file1.getOriginalFilename();
		if(StringUtil.isBlank(filename)){
			return null;
		}
		String nfile=System.currentTimeMillis()+filename.substring(filename.indexOf("."));
		//保存Eclipse中路径
		String absPath=session.getServletContext().getRealPath("/")+"/"+dir;
		//数据库中路径
		String path=request.getContextPath()+"/"+dir+"/"+nfile;
		File file=new File(absPath,nfile);
		if(!file.exists()){
			file.mkdirs();
		}
		try {
			file1.transferTo(file);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return path;
	}
	
	/**
	 * 删除数据库中url中保存的文件
	 * @param fileUrl 数据库中文件地址
	 * @param dir 项目中实际保存文件的 文件夹
	 * @param session HTTPSession
	 * @return
	 */
	public static boolean delFile(String fileUrl,String dir,HttpSession session){
		boolean flag=false;
		if(fileUrl==null){
			return true;
		}
		String name=fileUrl.substring(fileUrl.lastIndexOf("/"));
		if(!StringUtil.isBlank(fileUrl)){
			String local=session.getServletContext().getRealPath(dir);
			//删除栏目图片信息
			File f=new File(local,name);
			if(f.exists()){
				flag=f.delete();
			}
		}
		return flag;
	}
}
