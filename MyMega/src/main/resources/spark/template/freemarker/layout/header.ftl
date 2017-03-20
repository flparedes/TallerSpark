<header class="head" id="h1">
  <div class="text">
      <h1>${(textoCabecera)!"MyMega"}</h1>    
  </div>
  <div class="login">
  	<#if user??>
		<span>Bienvenido ${user}</span><br/>
		<span><a href="/logout">Salir</a></span>
	<#else>
		<span>Usuario anónimo</span><br/>
    	<span><a href="/login">Acceder</a></span>
	</#if>
  </div>
</header>