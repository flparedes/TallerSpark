<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
	<div class="linea">Bienvenido a MyMega.</div>
    <#if documents??>
		<#list documents as document>
			<div class="label">${document.name}</div>
			<div class="data">${document.id}</div>
		</#list>
	</#if>
	
</@layout.myLayout>