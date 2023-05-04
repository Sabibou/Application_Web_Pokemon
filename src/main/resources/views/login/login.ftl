<!DOCTYPE html>


<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PokExchange</title>
		<link rel="stylesheet" type="text/css" media="all" href="/css/reset.css">
		<link rel="stylesheet" type="text/css" media="all" href="/css/base.css">

    </head>

	
	<body>

			<main>

				<h1>Se connecter</h1>
                <!--method="post" action="./htbin/login.py"-->
				<form id="form" method="post" action="/login">
					<div class="input">
						<label for="username">User Name</label>
						<input id="username" type="text" name="username"/>
						<#if username??>
							<p>Mauvais nom d'utilisateur</p>
						</#if>
					</div>
					<div class="input">
						<label for="userpwd">Password</label>
						<input id="userpwd" type="password" name="userpwd"/>
						<#if mdp??>
							<p>Mauvais mot de passe</p>
						</#if>
					</div>
					
					<p><input type="submit" value="Login" /><input type="reset" value="Clear" /></p>
				</form>

                <p id="response"></p>
                <!--<a id="register" href="register.html">Créer un compte</a>-->


			</main>

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

