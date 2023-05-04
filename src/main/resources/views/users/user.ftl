<#ftl encoding="utf-8">

<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PokExchange</title>
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
            <li class="grow">
                <a href="/disconnect">
                    Se déconnecter
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

<ul class="list">
    <#list users as user>
        <li class="elt" id="user">
            <a href="/users/${user.id}">
                <img src="/img/Ash.png" alt="Avatar">
                <span>${user.pseudo}</span>
            </a>
        </li>
    </#list>
</ul>

<a href="/users">Accèder à la page des utilisateurs</a>

<#if user_page.id == main_user.id>
    <h2>Mes Pokemons</h2>
<#else>
    <h2>Ses Pokemons</h2>
</#if>

<ul class="list" id="pokemons">
        <#list pokemons as pokemon>
                <li class="elt" id="pokemon">
                    <a href="/pokemon/${pokemon.id}">
                        <img src=${pokemon.sprite}>
                       <span>${pokemon.name}</span>
                    </a>
                    level : ${pokemon.level}
                    <form action="/pokemon/${pokemon.id}/lvl_up" method="post">
                        <input class="grow" type="submit" value="Lvl Up">
                    </form>
                </li>
        </#list>
</ul>
<#if user_page.id == main_user.id>
    <h2>Mes Echanges</h2>
<#else>
    <h2>Ses Echanges</h2>
</#if>

<ul class="list">
    <#list exchanges as exchange>
        <li class="elt" id="exchange">
            <div>
                <a href="/echanges/${exchange.id}">
                    <img src=${exchange.pokemon.sprite}>
                </a>
                <#if main_user.id == exchange.pokemon.userId>
                    <form action="/echanges/${exchange.id}/cancel" method="post">
                        <input class="grow" type="submit" value="Cancel">
                    </form>
                </#if>
            </div>
        </li>
    </#list>
</ul>

<a href="/echanges/all">Voir tous les échanges</a>

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
