package com.xy.servlet.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xy.bean.JJLBillQuery;
import com.xy.dao.JJLBillQueryDao;

/**
 * Servlet implementation class SaveOrderServlet
 */
public class SaveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JJLBillQueryDao dao ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String list = request.getParameter("OrderList");
		Gson gson = new Gson();
		List<JJLBillQuery> orderList = null;
		
		try {
			orderList = (List<JJLBillQuery>) new JSONArray(list);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (orderList != null && orderList.size() >0) {
			for (int i = 0; i < orderList.size(); i++) {
				JJLBillQuery query = orderList.get(i);
				dao.saveJJLBill(query);
			}
			out.print("success");
		}else {
			out.print("fail");
		}
//		out.print(payResult);
		out.flush();
		out.close();
	}

}
