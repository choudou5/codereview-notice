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
    <link rel='stylesheet' href='/css/fonts.googleapis.com.css' type='text/css'></head>
    <link rel='stylesheet' href='/css/index.css' type='text/css'></head>
  <body>
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
                  	  <c:set var="groupCount" value="${fn:length(groupList) }"/>
	                  <c:forEach var="group" items="${groupList }" varStatus="status">
	            		<c:set var="index" value="${status.index+1 }"/>
	            		<a href="#section-${index }">${group }</a>
	            		<c:if test="${index ne groupCount }">&nbsp;|&nbsp;</c:if>
	            	  </c:forEach>
            	  </p>
                </div>
                <div class="hYQYcF" data-reactid="18">
                  <span class="jcrkPC" data-reactid="19"></span>
                  <div class="jEjczF" data-reactid="22">
                    <a title="分享到微信" href="#shares" data-reactid="23">
                    	<svg viewBox="-40 -60 500 512">
                         	<ellipse cx="150" cy="150" rx="150" ry="110" style="fill:rgb(255,255,255);stroke:rgb(255,255,255);stroke-width:2"></ellipse>
						    <circle cx="90" cy="105" r="15" style="fill:#08c406;stroke:#08c406;stroke-width:2"></circle>
						    <circle cx="210" cy="105" r="15" style="fill:#08c406;stroke:#08c406;stroke-width:2"></circle>
						    <polygon points="50,260 80,195 180,200" style="fill:#fff;stroke:#fff;stroke-width:1"></polygon>
						
						    <ellipse cx="268" cy="228" rx="125" ry="90" style="fill:#08c406;stroke:#08c406;stroke-width:2"></ellipse>
						    <ellipse cx="270" cy="230" rx="125" ry="90" style="fill:rgb(255,255,255);stroke:rgb(255,255,255);stroke-width:2"></ellipse>
						    <circle cx="220" cy="200" r="10" style="fill:#08c406;stroke:#08c406;stroke-width:2"></circle>
						    <circle cx="320" cy="200" r="10" style="fill:#08c406;stroke:#08c406;stroke-width:2"></circle>
						    <polygon points="330,260 350,320 300,300" style="fill:#fff;stroke:#fff;stroke-width:1"></polygon>
						</svg>
                    </a>
                  </div>
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
	                <div class="item kKOtfr" data-reactid="188">
	                  <h3 class="item-title jPzqaA" data-reactid="189">
	                    <a href="http://uibreakfast.com/40-learning-projects-sacha-greif/" data-reactid="190">UI Breakfast Podcast</a>
	                    <span class="fgqZDe" data-reactid="191">2016</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="195">
	                  <h3 class="item-title jPzqaA" data-reactid="196">
	                    <a href="https://devchat.tv/react-native-radio/43-the-state-of-javascript-and-react-native-with-sacha-greif/" data-reactid="197">React Native Radio</a>
	                    <span class="fgqZDe" data-reactid="198">2016</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="202">
	                  <h3 class="item-title jPzqaA" data-reactid="203">
	                    <a href="https://www.discovermeteor.com/podcast" data-reactid="204">Discover Meteor Podcast</a>
	                    <span class="fgqZDe" data-reactid="205">2015</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="209">
	                  <h3 class="item-title jPzqaA" data-reactid="210">
	                    <a href="http://www.binpress.com/blog/2015/07/21/podcast-39-sacha-greif/" data-reactid="211">Binpress Podcast</a>
	                    <span class="fgqZDe" data-reactid="212">2015</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="216">
	                  <h3 class="item-title jPzqaA" data-reactid="217">
	                    <a href="http://www.productpeople.tv/63" data-reactid="218">Product People</a>
	                    <span class="fgqZDe" data-reactid="219">2014</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="223">
	                  <h3 class="item-title jPzqaA" data-reactid="224">
	                    <a href="https://changelog.com/podcast/91" data-reactid="225">The Changelog</a>
	                    <span class="fgqZDe" data-reactid="226">2013</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="230">
	                  <h3 class="item-title jPzqaA" data-reactid="231">
	                    <a href="http://upfrontpodcast.com/2013/04/12/episode12.html" data-reactid="232">Upfront Podcast</a>
	                    <span class="fgqZDe" data-reactid="233">2013</span></h3>
	                </div>
	                <div class="item kKOtfr" data-reactid="237">
	                  <h3 class="item-title jPzqaA" data-reactid="238">
	                    <a href="http://theindustry.cc/2012/05/22/10-facebook-ipod-the-toolbox-launches-parse-for-ios-and-android-and-say-hello-to-octicons/" data-reactid="239">The Industry</a>
	                    <span class="fgqZDe" data-reactid="240">2012</span></h3>
	                </div>
	              </div>
	            </div>
	          </section>
          	
          </c:forEach>
          
           <div id="shares" class="shares">
	          <div>
	          	<span>分享到微信</span>
	          	<label id="shareWeixin"></label>
	          </div>
           </div>
		   <div class="kRmhrp" data-reactid="302">
	          <p>Code Review Notice · Version ${version } · by choudoufu<a href="#">Fork me on github</a></p>
           </div>
        </div>
      </div>
    </div>
    
    <script type="text/javascript" src="/js/qrcode.min.js"></script>
    <script type="text/javascript">
    
	    window.onload = function() {
	        (function() {
	            //获取放置微信二维码的DIV
	           // var content = document.getElementById("qrcode");
	            //设置属性
	            var qrcode = new QRCode('shareWeixin', {
					width: 180,
					height: 180,
					colorDark : '#000000',
					colorLight : '#ffffff',
					correctLevel : QRCode.CorrectLevel.H
				});
	            //设置二维码内容
	            qrcode.makeCode("http://blog.csdn.net/konaji");
	        })();
	    }

    </script>
    
  </body>

</html>