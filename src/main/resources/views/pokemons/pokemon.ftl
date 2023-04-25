<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

<a href="/users/${main_user.id}">
    ${main_user.id}
    ${main_user.pseudo}
</a>

<h1>${pokemon.name}</h1>

user id : ${pokemon.userId}
id : ${pokemon.id}
name : ${pokemon.name}
level : ${pokemon.level}
sprite : <img src=${pokemon.sprite}>

<#if !isExchangeable && main_user.id == pokemon.userId>

    <h2>Mettre </h2>
    <form action="/echanges/add/${pokemon.id}" method="post">
        <input type="text" name="pokemonName" id="pokemonName">
        <input type="submit" value="Envoyer">
    </form>
<#elseif main_user.id == pokemon.userId>
    Pokemon déjà sur le marché
<#else>
</#if>


</body>

</html>
