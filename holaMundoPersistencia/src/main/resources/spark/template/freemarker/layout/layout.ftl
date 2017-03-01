<#macro myLayout>
  <!DOCTYPE html>
  	<meta charset="utf-8" />
	<html>
	<head>
	    <title>Persistencia con Hibernate</title>
	    <meta content="text/html; charset=utf-8" http-equiv="content-type">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="/stylesheets/style.css">
	</head>
    <body>
	    <div id="page-wrap">
	      <#include "header.ftl"/>
	      <div id="content">
	          <#include "menu.ftl"/>
	          <br/>
	          <section class="main" id="s1">
	          <#nested/>
	          </section>
          </div>
          
          <#include "footer.ftl"/>
        </div>
    </body>
  </html>
</#macro>