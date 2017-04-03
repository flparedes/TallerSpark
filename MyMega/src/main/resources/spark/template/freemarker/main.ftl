<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
	<div class="linea">Bienvenido a MyMega.</div>
	<div class="linea">&nbsp;</div>
    <#if documents??>
		<#list documents as document>
		<div class="linea">
			<div class="label">${document.name}</div>
			<div class="data">
				<a href="/download/${document.id}">Descargar</a>
				<#if user?? && user == document.user.name>
					<a href="#" 
					  onclick="if (confirm('¿Seguro que quieres borrar el documento?')) { window.location='/delete/${document.id}';}">
					    Borrar
					</a>
				</#if>
			</div>
		</div>
		</#list>
	</#if>
	
</@layout.myLayout>