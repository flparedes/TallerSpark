<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/form" method="POST">
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

    <div class="label">Texto</div>
    <div class="data">
    	<textarea name="texto" cols="80" rows="10">${texto!""}</textarea>
    </div>
    
    <#if coches??>
    <div class="label">Coches</div>
	<div class="data">
    	<#list coches as coche>
		    <input type='checkbox' name='coches' value='${coche["codigo"]}'/> ${coche.nombre}<br />
		</#list>
    </div>
	</#if>

    <input type="submit" value="Guardar">
</form>

</@layout.myLayout>