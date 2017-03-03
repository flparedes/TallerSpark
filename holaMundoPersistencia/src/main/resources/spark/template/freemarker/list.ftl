<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
    <#if coches??>
    <div class="label">Fabricante</div>
	<div class="data">Modelo</div>
	<div class="buttons">Acciones</div>
	<#list coches as coche>
		<div class="label">${coche.manufacturer.name}</div>
		<div class="data">${coche.name}</div>
		<div class="buttons">
			<form action="/car/delete/${coche.id}" method="POST">
				<input type="submit" value="Borrar">
			</form>
		</div>
	</#list>
	<#else>
	<div class="linea error">No se han encontrado resultados.</div>
	</#if>
	
</@layout.myLayout>