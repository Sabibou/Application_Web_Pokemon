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
</body>

</html>