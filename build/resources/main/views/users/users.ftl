<#ftl encoding="utf-8">

<html lang="fr">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PokExchange</title>
  <link rel="stylesheet" type="text/css" media="all" href="/css/reset.css">
  <link rel="stylesheet" type="text/css" media="all" href="/css/base.css"></li>
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

<a href="/users/${main_user.id}">
  ${main_user.id}
  ${main_user.pseudo}
</a>

<h2>Autres Utilisateurs</h2>

<ul>
  <#list users as user>
  <li><a href="/users/${user.id}">${user.id} - ${user.pseudo} </a>
    ${user.lastConnection}
    ${user.nbPokemonXP}
    ${user.password}
  </li>
</#list>
</ul>

<a href="/disconnect">Se déconnecter</a>

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