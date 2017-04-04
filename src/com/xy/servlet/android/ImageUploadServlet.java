package com.xy.servlet.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xy.bean.JJLImage;
import com.xy.bean.JJLUser;
import com.xy.dao.JJLImageDao;
import com.xy.dao.JJLUserDao;
import com.xy.utils.DeleteFileUtil;

public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String[] namelist;
	private String nameStr=null;
	private String userid=null;
	private List<String> imgNameList= new ArrayList<String>();
	
	private JJLUserDao userDao = new JJLUserDao();
	private JJLUser user=null;
	private JJLImageDao imageDao=new JJLImageDao();
	
	private String fileDir=null;
	private JJLImage image=null;
	private String strName="";
	
	private ServletConfig config;
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();//输出

		int flag=0;
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(isMultipart==true){
			try{
				FileItemFactory factory=new DiskFileItemFactory();
				
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				List<FileItem> fileItems=upload.parseRequest(request);
				Iterator<FileItem>iter=fileItems.iterator();
				
				String path = config.getServletContext().getRealPath("/");
				path=path.replace("JJLserver\\", "")+"Image\\";
				
				 
				DeleteFileUtil.deleteDirectory(path);
				image=new JJLImage();
				image.setUserID(userid);
				image.setName("");
				image.setAddTime("");
				imageDao.updateImageByUserID(image);
				
				imgNameList.clear();
				strName="";
				while(iter.hasNext()){
					FileItem item=(FileItem)iter.next();
					if(item.isFormField()){
						String name=item.getFieldName();
						String value=item.getString();						
						if(name.equals("namelist")){
							nameStr=value;
							System.out.println(name+ "," + value);
						}
					}else{
						String fileName=item.getName();
						if(fileName!=null){
							imgNameList.add(fileName);
							File file=new File(path+item.getName());
							if(!file.exists()){
								item.write(file);
								System.out.println("Success!");
								flag=1;
							}else{
								System.out.println("Exists!");
								flag=2;
							}
						}
					}
				}
 
				for(int i=0;i<imgNameList.size();i++){
					strName=strName + imgNameList.get(i) + ",";
				}
				
				strName=strName.substring(0, strName.length()-1);
				
				namelist=nameStr.split(",");
				for(int i=0;i<namelist.length;i++){					
					user=userDao.getUseridByUsername(namelist[i]);
					if(user!=null){
						
						//----------
						fileDir=path + File.separator + namelist[i];
						createDir(fileDir);
						File srcFile=new File(path);
						File[] files=srcFile.listFiles();
						for(int j=0;j<files.length;j++){
							String srcPath=files[j].getAbsolutePath();
							String targetPath=fileDir + File.separator + files[j].getName();
							copyFile(srcPath,targetPath);
						}
						//----------
						
						userid=user.getUserID();						
						image=new JJLImage();
						image.setUserID(userid);
						image.setName(strName);
						//System.out.println(strName);
						image.setPath("Image/" + namelist[i] + "/");
						image.setAddTime(dateFormat.format(new Date()));
						
						if(imageDao.queryImageUserID(userid)){							
							imageDao.updateImageByUserID(image); 
						}else{
							imageDao.addImage(image);
						}
					}else{
						//User=null; 没有找到user
					}
				}
				
				DeleteFiles(path);
												
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		Gson gson = new Gson();
		List<Map<String, Object>> listOK=null;
		Map<String, Object> mapJson =null;
		String resultOKJson =null;
		switch(flag)
		{
			case 0:
				listOK = new ArrayList<Map<String, Object>>();
				mapJson = new HashMap<String, Object>();	
				mapJson.put("flag", "0");
				listOK.add(mapJson);
				resultOKJson= gson.toJson(mapJson);
				out.print(resultOKJson);
				break;
			case 1:
				listOK = new ArrayList<Map<String, Object>>();
				mapJson = new HashMap<String, Object>();	
				mapJson.put("flag", "1");
				listOK.add(mapJson);
				resultOKJson= gson.toJson(mapJson);
				out.print(resultOKJson);
				break;
			case 2:
				listOK = new ArrayList<Map<String, Object>>();
				mapJson = new HashMap<String, Object>();	
				mapJson.put("flag", "2");
				listOK.add(mapJson);
				resultOKJson= gson.toJson(mapJson);
				out.print(resultOKJson);
				break;
		}

		out.flush();
		out.close();
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static boolean copyFile(String srcpath,String targetpath)
	        throws IOException {    
	    InputStream input = null;    
	    OutputStream output = null;    
	    try {
	    	File source=new File(srcpath);
	    	File dest=new File(targetpath);
	    	if(!source.isDirectory()){
				input = new FileInputStream(source);
				output = new FileOutputStream(dest);        
				byte[] buf = new byte[1024];        
				int bytesRead;        
				while ((bytesRead = input.read(buf)) > 0) {
				    output.write(buf, 0, bytesRead);
				}
				
				input.close();
		        output.close();
	    	}
	        return true;
	        
	    } catch (Exception e) {
			// TODO: handle exception
	    	System.out.println(e.getMessage());	    	
		}  
	    return false;
	}
	
	// 创建目录
	public boolean createDir(String destDirName) {
			File dir = new File(destDirName);
			if (dir.exists()) {// 判断目录是否存在
				//System.out.println("创建目录失败，目标目录已存在！");
				return false;
			}
			if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
				destDirName = destDirName + File.separator;
			}
			if (dir.mkdirs()) {// 创建目标目录
				System.out.println("创建目录成功！" + destDirName);
				return true;
			} else {
				//System.out.println("创建目录失败！");
				return false;
			}
		}
	
	private void DeleteFile(String path){
		File file=new File(path);
		if(file.exists()){
			file.delete();
		}
	}
	
	private void DeleteFiles(String path ){
		//==========
		File mfile=new File(path);
		try{
			if(mfile.isDirectory()){ // mfile.length()==9
				String name[]=mfile.list();
				for(int i=0;i<name.length;++i){
					File f=new File(path,name[i]);
					f.delete();
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//==========
	}
}
