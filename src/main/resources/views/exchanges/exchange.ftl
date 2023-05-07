<#ftl encoding="utf-8">

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PokExchange</title>
    <link rel="stylesheet" type="text/css" media="all" href="/css/reset.css">
    <link rel="stylesheet" type="text/css" media="all" href="/css/base.css">
    <link rel="stylesheet" type="text/css" media="all" href="/css/exchanges.css">
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
            <li class="grow">
                <a href="/pokedex">
                    Pokedex
                </a>
            </li>
            <li class="grow">
                <a href="/disconnect">
                    Se déconnecter
                </a>
            </li>
        </ul>
    </nav>
</header>

<h1>Exchange</h1>

<div id="exchangePage">
    <img src=${pokemon.sprite}>
    <div id="description">
        <p><em>ID Pokédex :</em> ${pokemon.pokedexId}</p>
        <p><em>Nom :</em> ${pokemon.name}</p>
        <p><em>Niveau :</em> ${pokemon.level}</p>
    </div>
</div>

<h2>Pokemons voulus</h2>

<ul id="pokemonWanted">
    <#list pokemonsWanted as pokemonWanted>
        <li class="elt">
            <a href="/pokedex/${pokemonWanted.pokedexId}">
                <img src=${pokemonWanted.sprite}>
                <span>${pokemonWanted.name}</span>
            </a>
            <#if main_user.id != pokemon.userId && pokemonWanted.id != -1>
                <form action="/echanges/${exchange.id}/accept/${pokemonWanted.id}" method="post">
                    <input type="submit" value="Echanger">
                </form>
            <#else>
                <em>Non possédé</em>
            </#if>
        </li>
    </#list>
</ul>

<#if main_user.id == exchange.pokemon.userId>
    <div>
        Ajouter pokemon voulu :
        <form action="/echanges/${exchange.id}/add" method="post">
            <input type="text" name="pokemonName" id="pokemonName">
            <input type="submit" value="Ajouter">
        </form>
    </div>
    Annuler l'échange :
    <form action="/echanges/${exchange.id}/cancel" method="post">
        <input type="submit" value="Cancel">
    </form>
</#if>

<footer class="footer">
    <h1>A propos</h1>
    <p>Nous sommes un site indépendant. Toute propriété intellectuelle appartient à Pokemon@Company.</p>
    <div class="container-footer">
        <div class="column">
            <h2>Infos</h2>
            <ul>
                <li><a href="http://web.com">Info1</a></li>
                <li><a href="http://web.com">Info2</a></li>
                <li><a href="http://web.com">Info3</a></li>
            </ul>
        </div>
        <div class="column">
            <h2>Aide</h2>
            <ul>
                <li><a href="http://web.com">Aide1</a></li>
                <li><a href="http://web.com">Aide2</a></li>
                <li><a href="http://web.com">Aide3</a></li>
            </ul>
        </div>
    </div>

    <p id="author">Site par Rodrigo Ferreira Rodrigues et M'hammed Salman Abibou</p>

</footer>

</body>

</html>
