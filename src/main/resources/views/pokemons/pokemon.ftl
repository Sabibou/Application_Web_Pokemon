<#ftl encoding="utf-8">

<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Site Pokemon</title>
    <link rel="stylesheet" type="text/css" media="all" href="/css/reset.css">
</head>

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

<form action="/pokemon/${pokemon.id}/lvl_up" method="post">
    <input type="submit" value="Lvl Up">
</form>

<#if !isExchangeable && main_user.id == pokemon.userId>

    <h2>Mettre sur le marché des échanges (écrire le nom des pokemons en anglais)</h2>
    <form action="/echanges/add/${pokemon.id}" method="post">
        <p>Rentrer le nom du pokemon voulu :</p>
        <input type="text" name="pokemonName" id="pokemonName">
        <input type="submit" value="Envoyer">
    </form>
<#elseif main_user.id == pokemon.userId>
    Pokemon déjà sur le marché
<#else>
</#if>

<#if main_user.id == pokemon.userId>

    <h2>Renommer le pokemon</h2>
    <form action="/pokemons/${pokemon.id}/rename" method="post">
        <p>Rentrer le nouveau nom du pokemon :</p>
        <input type="text" name="newName" id="newName">
        <input type="submit" value="Renommer">
    </form>
<#else>
</#if>

</body>

</html>
