<#import "../layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/login" method="POST">
    <#if error??>
    	<div class="label"></div>
    	<div class="data error">${error}</div>
	</#if>
	
    <div class="label">User</div>
    <div class="data">
    	<input type="text" name="user" placeholder="user" value="${user!""}">
	</div>
    
    <div class="label">Password</div>
    <div class="data">
    	<input type="password" name="pass" placeholder="password">
    </div>

    <div class="label"><input type="submit" value="Enviar"></div>
</form>

</@layout.myLayout>