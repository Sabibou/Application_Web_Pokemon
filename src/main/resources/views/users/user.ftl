<#ftl encoding="utf-8">

<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Site Pokemon</title>
    <link rel="stylesheet" type="text/css" media="all" href="/css/reset.css"></li>
    <link rel="stylesheet" type="text/css" media="all" href="/css/base.css"></li>
    <link rel="stylesheet" type="text/css" media="all" href="/css/users.css"></li>
</head>

<body xmlns="http://www.w3.org/1999/html">
<header class="en-tete">

    <a href="https://www.pokemon.com/fr">
        <img id="logo" src="/img/pokeball.png" alt="Pokeball">
    </a>

    <nav class="navbar">
        <ul class="navlist">
            <li class="grow">
                <a href="/users/${main_user.id}">
                    Page Utilisateur
                </a>
            </li>
            <li class="grow">
                <a href="/users">
                    Tous les utilisateurs
                </a>
            </li>
            <li class="grow">
                <a href="/echanges/all">
                    Tous les Echanges
                </a>
            </li>
        </ul>
    </nav>
</header>


<#if user_page.id == main_user.id>
    <h1>Votre Compte</h1>
<#else>
    <h1>Page utilisateur de ${user_page.pseudo}</h1>
</#if>


<h2>Autres Utilisateurs</h2>

<ul id="users">
    <#list users as user>
        <li id="user">
            <img src="/img/Ash.png" alt="Avatar">
            <a href="/users/${user.id}">${user.pseudo} </a>
        </li>
    </#list>
</ul>

<a href="/users">Accèder à la page des utilisateurs</a>

<#if user_page.id == main_user.id>
    <h2>Mes Pokemons</h2>
<#else>
    <h2>Ses Pokemons</h2>
</#if>

<ul>
        <#list pokemons as pokemon>
                <li>user id : ${pokemon.userId}
                    id : ${pokemon.id}
                    pokedex id : ${pokemon.pokedexId}
                    <a href="/pokemon/${pokemon.id}">name : ${pokemon.name}</a>
                    level : ${pokemon.level}
                    sprite : <img src=${pokemon.sprite}>
                    <form action="/pokemon/${pokemon.id}/lvl_up" method="post">
                        <input type="submit" value="Lvl Up">
                    </form>
                </li>
        </#list>
</ul>
<#if user_page.id == main_user.id>
    <h2>Mes Echanges</h2>
<#else>
    <h2>Ses Echanges</h2>
</#if>

<ul>
    <#list exchanges as exchange>
        <li><a href="/echanges/${exchange.id}">echange id : ${exchange.id}</a>
            pokedex_id : ${exchange.pokemon.pokedexId}
            sprite : <img src=${exchange.pokemon.sprite}>
        </li>
    </#list>
</ul>

<a href="/echanges/all">Voir tous les échanges</a>

<a href="/disconnect">Se déconnecter</a>
</body>

</html>
