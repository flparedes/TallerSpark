<#import "layout/layout.ftl" as layout>
<@layout.myLayout>    
    <#if coches??>
    <div class="label">Fabricante</div>
	<div class="data">Modelo</div>
	<#list coches as coche>
		<div class="label">${coche.manufacturer.name}</div>
		<div class="data">${coche.name}</div>
	</#list>
	<#else>
	<div class="linea error">No se han encontrado resultados.</div>
	</#if>
	
</@layout.myLayout>