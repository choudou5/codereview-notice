<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="manifest" href="/images/manifest.json" />
    <link rel="mask-icon" href="/images/safari-pinned-tab.svg" color="#eb613d" />
    <meta name="msapplication-config" content="/images/browserconfig.xml" />
    <meta name="theme-color" content="#ffffff" />
    <title data-react-helmet="true">Code Review Notice</title>
    <meta data-react-helmet="true" name="description" content="Code Review Notice" />
    <link rel='stylesheet' type='text/css' href='/css/fonts.googleapis.com.css'>
    <link rel='stylesheet' type='text/css' href='/css/stickUp.css'>
    <link rel='stylesheet' type='text/css' href='/css/index.css'>
    </head>
  <body>
    <%-- 服务器返回对象 --%>
  	<c:set var="groupCount" value="${fn:length(groupList) }"/>
  	<c:set var="goodNotices" value="${indexNoticeGroupVo.goods }"/>
  	<c:set var="badNotices" value="${indexNoticeGroupVo.bads }"/>
  	
    <div id="react-mount">
      <div class="iQbGqt" data-reactroot="" data-reactid="1" data-react-checksum="1732591574">
        <div data-reactid="2">
        
          <section id="section-0" class="section-intro gMyTHp" style="background-color:#EB613D;" data-reactid="4">
            <div class="fUkIQu" data-reactid="5">
              <svg data-reactid="6">
                <defs data-reactid="7">
                  <pattern id="xubtq" patternUnits="userSpaceOnUse" width="20" height="20" data-reactid="8">
                    <circle cx="10" cy="10" r="4" fill="transparent" stroke="rgba(0,0,0,0.13)" stroke-width="1" data-reactid="9"></circle>
                  </pattern>
                </defs>
                <rect style="fill:url(#xubtq);" x="0" y="0" height="3000" width="3000" data-reactid="10"></rect>
              </svg>
            </div>
            
            <div class="RTdSn" data-reactid="11">
              <div class="bzDtW" data-reactid="12">
                <a href="/" data-reactid="13" class="hoverNoLine">
                  <h1 class="dsCugZ" data-reactid="14">
                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" 
                    	width="220" height="100" x="0px" y="0px" viewBox="0 0 150 400" enable-background="new 0 0 400 400" xml:space="preserve">
                      <g id="Layer_2_copy">
                        <g>
                          <g>
                              <text font-style="normal" font-weight="bold" stroke="#000" 
                              	transform="matrix(1.2429540665934067,0,0,3.0655551642504424,-356.623449367830356,-50.783705945308284)  " xml:space="preserve" 
                              	text-anchor="start" font-family="黑体, sans-serif" font-size="68" 
                              	id="svg_4" y="80.56897" x="5.55507" stroke-width="0" fill="#fff">Code Review Notice</text>
                          </g>
                        </g>
                      </g>
                    </svg>
                  </h1>
                  <span data-reactid="15">Sacha Greif</span></a>
              </div>
              <div class="section-intro-contents huKWfn" data-reactid="16">
                <div class="hPJIuQ" data-reactid="17">
                  <p><strong>Code Review</strong>, 是提高编码能力的重要流程, Are you ready？</p>
                  <p>
	                  <c:forEach var="group" items="${groupList }" varStatus="status">
	            		<c:set var="index" value="${status.index+1 }"/>
	            		<a href="#section-${index }">${group }</a>
	            		<c:if test="${index ne groupCount }">&nbsp;|&nbsp;</c:if>
	            	  </c:forEach>
            	  </p>
                </div>
                <div class="hYQYcF" data-reactid="18">
                  <span class="jcrkPC" data-reactid="19"></span>
		          <%@include file="/WEB-INF/view/include/baidu_share.jsp" %>
                  <span class="jcrkPC" data-reactid="30"></span>
                </div>
              </div>
            </div>
          </section>
          
          <c:forEach var="group" items="${groupList }" varStatus="status">
            <c:set var="index" value="${status.index }"/>
            <c:set var="groupColor" value="${groupColors[index]}"/>
            <c:if test="${empty groupColor}">
          		<c:set var="groupColor" value="grey;border-top:4px solid #ffffff"/>
          	</c:if>
          	<section id="section-${index+1 }" class="section gMyTHp" style="background-color:${groupColor}" data-reactid="172">
	            <div class="fUkIQu" data-reactid="173">
	              <svg data-reactid="174">
	                <defs data-reactid="175">
	                  <pattern id="pattern-${index }" patternUnits="userSpaceOnUse" width="20" height="20" data-reactid="176">
	                   	 <c:set var="groupBgPattern" value="${groupBgPatterns[index] }"/>
	                   	 <c:if test="${empty groupBgPattern}">
				          	<c:set var="groupBgPattern" value="M 2.5,2.5l5,5M2.5,7.5l5,-5"/>
				          </c:if>
	                    <path d="${groupBgPattern }" fill="transparent" stroke-width="1" shape-rendering="auto" stroke="#CCCCCC" stroke-linecap="square" data-reactid="177"></path>
	                  </pattern>
	                </defs>
	                <rect style="fill:url(#pattern-${index });" x="0" y="0" height="3000" width="3000" data-reactid="178"></rect>
	              </svg>
	            </div>
	            <div class="jcZDsC" data-reactid="179">
	              <div class="section-icon jQxPsJ" data-reactid="180">
	                <div class="hLVIgY" data-reactid="181">
	                  <span data-reactid="182">
	                    <svg width="18px" height="23px" viewBox="0 0 18 23" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
	                      <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" stroke-linecap="round" stroke-linejoin="round">
	                        <g id="Group">
	                          <text font-weight="bold" xml:space="preserve" text-anchor="middle" font-family="黑体, sans-serif" font-size="22" id="svg_1" y="20" x="9" stroke-width="0" stroke="#ff0000" fill="#000000">${index+1 }</text>
	                        </g>
	                      </g>
	                    </svg>
	                  </span>
	                </div>
	              </div>
	              <div class="kiokDU" data-reactid="183">
	                <div class="iaCmgk" data-reactid="184">
	                  <h2 class="section-title dvtXOL" data-reactid="185">${group }</h2>
	                  <!-- <div class="gMgzUa" data-reactid="186"><p>说明.</p></div> -->
	                </div>
	              </div>
	              <div class="euuwmB" data-reactid="187">
	                <div class="item kKOtfr" data-reactid="195">
	               		<h3 class="item-title jPzqaA" data-reactid="189">Good</h3>
	               		<c:if test="${empty goodNotices[group] }">
	               			<p>
		               			<span class="fgqZDe">您还没创建任何信息哟！</span>
		               			<a href="?view=form&type=good" target="_blank">立即创建</a>
	               			</p>
	               		</c:if>
	               		<c:if test="${not empty goodNotices[group] }">
		               		<ul>
		               			<c:forEach var="notice" items="${goodNotices[group] }">
						        	<li><a href="?view=detail&id=${notice.id }" target="_blank">${notice.title }</a><span class="fgqZDe">${notice.createTime }</span></li>   
		               			</c:forEach>
						    </ul>
	               		</c:if>
	                    
	                </div>
	                <div class="item kKOtfr" data-reactid="195">
	                	<h3 class="item-title jPzqaA" data-reactid="189">Bad</h3>
	                	<c:if test="${empty badNotices[group] }">
	               			<p>
		               			<span class="fgqZDe">您还没创建任何信息哟！</span>
		               			<a href="?view=form&type=bad" target="_blank">立即创建</a>
	               			</p>
	               		</c:if>
	               		<c:if test="${not empty badNotices[group] }">
		               		<ul>
		               			<c:forEach var="notice" items="${badNotices[group] }">
						        	<li><a href="?view=detail&id=${notice.id }" target="_blank">${notice.title }</a><span class="fgqZDe">${notice.createTime }</span></li>   
		               			</c:forEach>
						    </ul>
	               		</c:if>
	                </div>
	              </div>
	            </div>
	          </section>
          	
          </c:forEach>
          
           <!-- START THE NAVBAR -->
		    <div class="navbar-wrapper">
		      <div class="navwrapper">
		      	<c:if test="${groupCount > 0 }">
			        <div class="navbar-static-right">
		              <ul class="nav navbar-nav">
		              	<li class="menuItem active"><a href="#section-0">︽</a></li>
		              	<c:forEach var="group" items="${groupList }" varStatus="status">
		            		<c:set var="index" value="${status.index+1 }"/>
		            		<li class="menuItem"><a href="#section-${index }">${group }</a></li>
		            	</c:forEach>
		                <li class="menuItem"><a href="#footer">︾</a></li>
		              </ul>
			        </div>
		        </c:if> 
		      </div><!-- End Navbar -->
		    </div> <!-- END NAVBAR -->
		    
		   <div id="footer" class="footer" data-reactid="302">
	          <p>Code Review Notice · Version ${version } · by choudoufu&nbsp;<a href="#">Fork me on github</a></p>
           </div>
        </div>
      </div>
    </div>
    <!-- <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script> -->
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/stickUp.js"></script>
    <script type="text/javascript" src="/js/qrcode.min.js"></script>
    <script type="text/javascript">
	    jQuery(function($) {
	        $(document).ready( function() {
	          //右侧导航菜单
        	  var groupCount = parseInt("${fn:length(groupList) }");
	          if(groupCount > 0){
	        	  var parts = {};
	        	  for(var i=0; i<=groupCount; i++){
	        		  parts[i] = "section-"+i;
	        	  }
	        	  var end = groupCount+1;
	        	  parts[end] = "footer";
	        	  $('.navbar-wrapper').stickUp({
	                  parts: parts,
	                  itemClass: 'menuItem',
	                  itemHover: 'active',
	                  topMargin: 'auto'
	              });
	          }
	        });
	      });
    </script>
  </body>
</html>