<#import "/layout/layout.ftl" as layout>
<@layout.myLayout>

<#if errors??>
	<div class="linea error">Se han producido errores de validación: </div>
	<#list errors as error>
    	<div class="linea">
	    	<div class="label"></div>
		    <div class="data error">${error}</div>
		</div>
	</#list>
	<div class="linea">&nbsp;</div>
</#if>

<form action="/new" method='POST' enctype='multipart/form-data'>
	<div class="label">Documento</div>
    <div class="data">
    	<input type='file' name='document'/>
	</div>
    <div class="label"><input type="submit" value="Enviar"></div>
</form>

</@layout.myLayout>