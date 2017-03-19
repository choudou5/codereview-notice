package com.dreamerpartner.codereview.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dreamerpartner.codereview.entity.NoticeEntity;
import com.dreamerpartner.codereview.lucene.LuceneUtil;
import com.dreamerpartner.codereview.service.NoticeService;
import com.dreamerpartner.codereview.util.JsonUtil;
import com.dreamerpartner.codereview.util.OSSClientUtil;
import com.dreamerpartner.codereview.util.PropertiesUtil;


/**
 * 代码审计 通告Servlet
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class CodeReviewNoticeServlet extends HttpServlet {
	
	protected static Log logger = LogFactory.getLog(CodeReviewNoticeServlet.class);
	
	private static final long serialVersionUID = 766312084434738984L;
	
	
	public final static List<String> GROUP_LIST = new ArrayList<String>(10);
	private static String ADMIN;
	public final static String[] GROUP_COLORS = new String[]{"#A3D2DF", "#C6F0D5", "#FFEEB2", "#E8C8ED", "#CCCCFF", "#FFCCCC","#F9D8C4", "#EEEEEE", "#999966", "#CC9999", "#FF9999", "#404448"};
	public final static String[] GROUP_BG_PATTERN = new String[]{
		"M 5 5 l 10 0 l 0 10 l -10 0 Z", 
		"M 5,5l10,10M15,5l10,-10 M5,15l-10,10M15,25l10,-10 M-5,5l10,-10", 
		"M 0 7.5 l 7.5 0 l 0 -7.5 M 22.5 30 l 0 -7.5 l 7.5 0 M 7.5 15 l 0 7.5 l 7.5 0 M 15 7.5 l 7.5 0 l 0 7.5", 
		"M 5,15l5,-10l5,10", 
		"M 0,20 l 20,-20 M -5,5 l 10,-10 M 15,25 l 10,-10", 
		"M 0 5 l 5 0 l 0 -5 M 15 20 l 0 -5 l 5 0 M 5 10 l 0 5 l 5 0 M 10 5 l 5 0 l 0 5", 
		"M 5,5l10,10M5,15l10,-10", 
		"M 2.5,2.5l5,5M2.5,7.5l5,-5",
		"M 15 15 l 120 0 l 0 120 l -120 0 Z",
		"M10-5-10,15M15,0,0,15M0-5-20,15",
		"M 8,0 l 8,0 l 4,6.928203230275509 l -4,6.928203230275509 l -8,0 l -4,-6.928203230275509 Z M 0,6.928203230275509 l 4,0 M 24,6.928203230275509 l -4,0",
		"M 0 5 c 1.25 -2.5 , 3.75 -2.5 , 5 0 c 1.25 2.5 , 3.75 2.5 , 5 0 M -5 5 c 1.25 2.5 , 3.75 2.5 , 5 0 M 10 5 c 1.25 -2.5 , 3.75 -2.5 , 5 0",};
	
	public final static String GROUP_KEY = "groupKey";
	public final static String FILE_PATH = "/WEB-INF/view/";
	
	public final static String PAGE_INDEX = "index";
	public final static String PAGE_DETAIL = "detail";
	public final static String PAGE_FORM = "form";
	public final static String PAGE_FORM_SAVE = "formSave";
	public final static String PAGE_AJAX_VALID_ACCOUNT = "ajaxValidAccount";
	
	public CodeReviewNoticeServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void init() throws ServletException {
		logger.debug("begin init CodeReviewNoticeServlet.");
		super.init();
		
		//初始化 属性资源文件
		PropertiesUtil.init("/system.properties");
		
		initParam();
		
		logger.debug("end init CodeReviewNoticeServlet.");
	}
	
	private void initParam() throws ServletException{
		//初始化 分组
		if(GROUP_LIST.isEmpty()){
			String[] groupKeys = PropertiesUtil.getStringArray("group.keys");
			if(groupKeys == null)
				throw new ServletException("请初始化 system.properties 里的 group.keys 参数。");
			GROUP_LIST.addAll(Arrays.asList(groupKeys));
		}
		
		//初始化 系统账号
		if(StringUtils.isBlank(ADMIN)){
			String sysAdmin = PropertiesUtil.getString("system.admin");
			if(StringUtils.isBlank(sysAdmin))
				throw new ServletException("请初始化 system.properties 里的 system.admin 参数。");
			ADMIN = sysAdmin;
		}
		
		//初始化  索引目录
		LuceneUtil.initIndexDir();
		NoticeService.initIndex();//初始化索引
		//初始化 OSS
		OSSClientUtil.init();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("version", "1.0.0");
		String view = request.getParameter("view");
		if(StringUtils.isBlank(view))
			view = PAGE_INDEX;
		
		switch (view) {
		case PAGE_INDEX:
			Action.viewIndex(request, response);
			break;
		case PAGE_DETAIL:
			Action.viewDetail(request, response);
			break;
		case PAGE_FORM:
			Action.viewForm(request, response);
			break;
		case PAGE_FORM_SAVE:
			Action.formSave(request, response);
			break;
		case PAGE_AJAX_VALID_ACCOUNT:
			Action.ajaxValidAccount(request, response);
			break;
		default:
			Action.viewIndex(request, response);
		}
	}
	
	
	static class Action{
		
		/**
		 * 首页
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public static void viewIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			request.setAttribute("indexNoticeGroupVo", NoticeService.getIndexNoticeGroupVo());
			request.setAttribute("groupList", GROUP_LIST);
			request.setAttribute("groupColors", GROUP_COLORS);
			request.setAttribute("groupBgPatterns", GROUP_BG_PATTERN);
			request.getRequestDispatcher(FILE_PATH+"index.jsp").forward(request, response);
		}
		
		/**
		 * 详情
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException 
		 */
		public static void viewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				request.setAttribute("notice", NoticeService.get(id));
			}
			request.getRequestDispatcher(FILE_PATH+"detail.jsp").forward(request, response);
		}
		
		/**
		 * 新增
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException 
		 */
		public static void viewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			response.setHeader("Cache-Control","no-cache"); 
			response.setHeader("Pragma","no-cache"); 
			response.setDateHeader("Expires", 0); 
			
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("groupKey", request.getParameter("groupKey"));
			request.setAttribute("groupList", GROUP_LIST);
			request.getRequestDispatcher(FILE_PATH+"form.jsp").forward(request, response);
		}
		
		/**
		 * 保存公告
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public static void formSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			NoticeEntity entity = buildNoticeEnitty(request);
			logger.debug("entity:"+entity);
			//参数校验
			if(NoticeEntity.validate(entity)){
				writeJson(response, 403, "提交参数不完整！请填写完整.");
				return;
			}
			//权限校验
			if(!isPermission(request)){
				writeJson(response, 403, "账号不正确！请联系管理员.");
				return;
			}
			
			//保存
			try {
				NoticeService.save(entity);
				writeJson(response, 200, "保存成功！");
			} catch (Exception e) {
				writeJson(response, 400, "失败成功！", e);
			}
		}
		
		/**
		 * 异步验证 账号
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException 
		 */
		public static void ajaxValidAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String adminAccount = request.getParameter("adminAccount"); // AJAX验证，成功返回true
			if (StringUtils.isNotBlank(adminAccount)){
				response.getOutputStream().print(adminAccount.equals(ADMIN)?"true":"false");
			}else{
				response.getOutputStream().print("false");
			}
		}
		
		
		private static NoticeEntity buildNoticeEnitty(HttpServletRequest request){
			String groupKey = request.getParameter("groupKey");
			if(StringUtils.isBlank(groupKey)){
				return null;
			}
			NoticeEntity entity = new NoticeEntity();
			entity.setGroupKey(groupKey);
			entity.setTitle(request.getParameter("title"));
			entity.setContent(request.getParameter("content"));
			entity.setType(request.getParameter("type"));
			return entity;
		}
		
		
		/**
		 * 权限校验
		 * @param request
		 * @return
		 */
		private static boolean isPermission(HttpServletRequest request){
			String account = request.getParameter("adminAccount");
			return ADMIN.equals(account)?true:false;
		}
		
		/**
		 * 输出json
		 * @param response
		 * @param status 状态
		 * @param content 输出内容
		 */
		private static void writeJson(HttpServletResponse response, int status, Object content){
			writeJson(response, status, content, null);
		}
		
		/**
		 * 输出json
		 * @param response
		 * @param status 状态
		 * @param content 输出内容
		 * @param ex
		 */
		private static void writeJson(HttpServletResponse response, int status, Object content, Exception ex){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			Map<String, Object> result = new HashMap<String, Object>(2);
			result.put("status", status);
			result.put("content", content);
			if(ex != null){
				result.put("error", ex.getMessage());
			}
			PrintWriter out = null;
			try {
			    out = response.getWriter();
			    out.write(JsonUtil.toString(result));
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) out.close();
			}
		}
	}
	
}