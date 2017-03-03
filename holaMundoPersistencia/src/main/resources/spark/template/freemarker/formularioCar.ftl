<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/car/new" method="POST">
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
	
    <div class="label">Fabricante</div>
    <div class="data">
    	<select name="fabricante" value="${fabricante!""}">
		    <#if manufacturers??>
		    <h2>Coches</h2>
			<#list manufacturers as manufacturer>
			    <option value="${manufacturer["id"]}">${manufacturer.name}</option>
			</#list>
			<#else>
			<option value="">No hay Fabricantes</option>
			</#if>
	    </select>
	</div>
    
    <div class="label">Modelo</div>
    <div class="data">
    	<input type="text" name="nombre" size="120" value="${nombre!""}">
    </div>

    <div class="label"><input type="submit" value="Guardar"></div>
</form>

</@layout.myLayout>