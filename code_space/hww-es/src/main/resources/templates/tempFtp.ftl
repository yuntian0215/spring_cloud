<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Style-Type" content="text/css"/>
    <title></title>
    <style type="text/css">
        body {
            font-family: pingfang sc light;
        }
        .center{
            text-align: center;
            width: 100%;
        }
        
        #cover,.content{
            padding: 30pt;
        }
        #cover{
            text-align: center;
        }
        #main img{
            width: 100%;
        }
        #cover #covertitle{
            color: #f47c00;
            padding-top: 48pt;
            padding-bottom: 157.5pt;
        }
        #hww{
            border: 1px solid #686868;
            width: 308px;
            margin: 0 auto 31.5pt;
            color:rgb(255,255,255);
            font-size:16pt;
            background: #f47c00;
        }
        #hww~p{
            font-size: 16pt;
            margin-bottom: 152.5pt;
        }
        .ctitle,.ctitle~p{
            padding-bottom: 31.5pt;
        }
        .newstitle{
            font-size: 14pt;
        }
        .newscontent{
            font-size: 12pt;
        }
        .tablediv{
            border: 1px solid #000;
            text-align: center;
        }
        .order{
            width:10%
        }
        .date{
            width: 20%
        }
        
        .tablecontent.titlemain{
            text-align: left;
        }
        .titlemain{
            padding: 5px;
        }
        .titlemain p{
            padding-bottom: 5px;
        }
        .titlemain span{
            font-size: 11pt;
        }
    </style>
</head>
<body>
<div class="page">
    <div id="cover">
    	<h1 id="covertitle" style="font-size: 36pt">摘要版</h1>
        <p id="hww">海外网(舆情)${newDate}</p>
        <p>${date}</p>
    </div>
    <div class="content">
	    <div>
	        <h3 class="ctitle">一、监测概述</h3>
	        <p>对于舆情信息中具有潜在危害的事件及情况应给予关注并积极处理，防止不良影响产生及扩散。此外，密切关注此前敏感预警事件的发展情况，及时制定有效应对措施。鉴于监测结果中负面舆情时有发生，  应吸取相关经验教训，做好预防和处理工作</p>
	    </div>
	    
	    <div>
	        <h3 class="ctitle">二、主要舆情</h3>
	        <div>
	            <div>
	                <h3 class="ctitle">标题与摘要</h3>
	            </div>
	            <div>
	            	<div>
		            	<#list page as item>
		                <div class="tablediv tablecontent titlemain">
		                    <p class="newstitle">${item.title}</p>
                            <p class="newscontent">${item.abstract}</p>
                            <span>${item.inserDate}</span><span> ${item.source}</span>
		                </div>
		                </#list>
	                </div>
	            </div>
	        </div>
	     </div>
     </div>
     
</div>

</body>
</html>