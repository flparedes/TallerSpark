<#macro myLayout>
  <!DOCTYPE html>
  	<meta charset="utf-8" />
	<html>
	<head>
	    <title>Hola mundo</title>
	</head>
    <body style="width:90%;height:100%">
      <table border="1" cellspacing="0" cellpadding="0" style="width:100%;height:100%">
        <tr>
          <td colspan="2">
            <#include "header.ftl"/>		
          </td>
        </tr>
        <tr>
          <td>
            <#include "menu.ftl"/>
          </td>
          <td>
            <#nested/>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <#include "footer.ftl"/>
          </td>
        </tr>
      </table>
    </body>
  </html>
</#macro>