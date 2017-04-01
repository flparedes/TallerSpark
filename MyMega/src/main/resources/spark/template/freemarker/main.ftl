<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
	<div class="linea">Bienvenido a MyMega.</div>
	<div class="linea">&nbsp;</div>
    <#if documents??>
		<#list documents as document>
		<div class="linea">
			<div class="label">${document.name}</div>
			<div class="data"><a href="/download/${document.id}">Descargar</a></div>
		</div>
		</#list>
	</#if>
	
</@layout.myLayout>