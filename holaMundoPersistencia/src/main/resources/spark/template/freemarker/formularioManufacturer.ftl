<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/manufacturer/new" method="POST">
    <#if errores??>
    	<div class="linea error">Se han producido errores de validación: </div>
    	<#list errores as error>
	    	<div class="linea">
		    	<div class="label"></div>
			    <div class="data error">${error}</div>
			</div>
		</#list>
		<br/>
	</#if>
	
    <div class="label">Nombre</div>
    <div class="data">
    	<input type="text" name="nombre" size="120" value="${nombre!""}">
    </div>

    <div class="label">País</div>
    <div class="data">
    	<input type="text" name="pais" size="120" value="${pais!""}">
    </div>

    <input type="submit" value="Guardar">
</form>

</@layout.myLayout>