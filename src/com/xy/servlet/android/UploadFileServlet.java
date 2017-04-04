package com.xy.servlet.android;


import java.io.File;
import java.io.IOException;
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
import com.xy.bean.JJLUser;
import com.xy.bean.JJLVideo;
import com.xy.dao.JJLUserDao;
import com.xy.dao.JJLVideoDao;

public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String[] namelist;
	private String tempstr=null;
	private String fileName=null;
	private String userid=null;
	
	private ServletConfig config;
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();//���
		
		JJLUserDao userDao = new JJLUserDao();
		JJLVideoDao videoDao=new JJLVideoDao();
		JJLUser user=null;
		
		int flag=0;
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(isMultipart==true){
			try{
				FileItemFactory factory=new DiskFileItemFactory();
				
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				List<FileItem> fileItems=upload.parseRequest(request);
				Iterator<FileItem>iter=fileItems.iterator();
				
				//C:\websoft\Tomcat7\webapps\Image
				//String path="C:\\websoft\\Tomcat7\\webapps\\Video\\";
				String path = config.getServletContext().getRealPath("/");
				path=path.replace("JJLserver\\", "")+"Video\\";
				
				while(iter.hasNext()){
					FileItem item=(FileItem)iter.next();
					if(item.isFormField()){
						String name=item.getFieldName();
						String value=item.getString();						
						value=new String(value.getBytes("iso-8859-1"),"utf-8");
						System.out.println(name+ "," + value);
						tempstr=value;
					}
					else{
						
						fileName=item.getName();
						if(fileName!=null){
							//----------
							JJLVideo video=null;
							namelist=tempstr.split(",");
							user=new JJLUser();
							for(int i=0;i<namelist.length;++i){
								user=userDao.getUseridByUsername(namelist[i]);
								if(user!=null){
									userid=user.getUserID();
									
									//����ļ�
									//-----------
									String fileDir=path + File.separator + namelist[i];
									createDir(fileDir);
									DeleteFiles(fileDir);//ɾ�������ļ� for test
									File file=new File(fileDir+File.separator + fileName);
									if(!file.exists()){
										item.write(file);
										System.out.println("Success!");
										flag=1;
									}else{
										file.delete();
										item.write(file);								
										System.out.println("Exists and Replace!");
										flag=2;
									}
									//-----------
	
									video=new JJLVideo();
									video.setUserID(userid);
									video.setName(fileName);
									video.setPath("Video/" + namelist[i] + "/");
									video.setAddTime(dateFormat.format(new Date()));
									
									//�����ݿ�
									//-----------------------
									if(videoDao.queryVideoUserID(userid)){
										videoDao.updateVideoByUserID(video);
									}else
									{
										videoDao.addVideo(video); 
									}
									//-----------------------
								}else{
									//User=null; û���ҵ�user
								}
							}
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
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
	
	// ����Ŀ¼
	public boolean createDir(String destDirName) {
			File dir = new File(destDirName);
			if (dir.exists()) {// �ж�Ŀ¼�Ƿ����
				System.out.println("����Ŀ¼ʧ�ܣ�Ŀ��Ŀ¼�Ѵ��ڣ�");
				return false;
			}
			if (!destDirName.endsWith(File.separator)) {// ��β�Ƿ���"/"����
				destDirName = destDirName + File.separator;
			}
			if (dir.mkdirs()) {// ����Ŀ��Ŀ¼
				System.out.println("����Ŀ¼�ɹ���" + destDirName);
				return true;
			} else {
				System.out.println("����Ŀ¼ʧ�ܣ�");
				return false;
			}
		}
	
	private void DeleteFile(String path){
		File file=new File(path);
		if(file.exists()){
			file.delete();
		}
	}
	
	private void DeleteFiles(String path){
		//==========
		File mfile=new File(path);
		try{
			if(mfile.isDirectory()){
				String name[]=mfile.list();
				for(int i=0;i<name.length;++i){
					File f=new File(path,name[i]);
					f.delete();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//==========
	}
}
