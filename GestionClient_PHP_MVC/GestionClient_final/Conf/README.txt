1. Applications
	
	Il est impératif de respecter l'arborescence suivant :
	
		Apps
			- UneApp
				- Views
				- Actions
				- Template
			- UneAutreApp
				- Views
				- Actions
				- Template
	
	Le dossier Views contiendra toutes vos vues, d'extension .php
	Le dossier Actions contiendra toutes vos classes d'actions (extension .class.php)
		Il y impératif d'y déclarer le namespace dans chaqunes
	Le dossier Template contiendra votre fichier layout.php, définissant la structure HTML / PHP à reproduire pour toutes les pages de cette application
	

2. Configuration

	a) actions.xml	

	mapping des différentes actions de chaque application
			si une classe d'action n'est pas mappée dans ce fichier, elle ne sera pas accessible
			
	racine : <conf>
	balise <actions> : groupement des actions par application
		app = nom de l'application
		namespace = nom du namespace contenant toutes les classes d'actions
		chaque balise actions doit contenir une balise <action name="default" /> definissant l'action par défaut pour cette application
	
	balise <action> : définition d'une action
		name = nom de l'action (url utilisée)
		class = classe d'action
	
	b) log4php.xml
	
	configuration du logging
	
	cf : http://logging.apache.org/log4php/quickstart.html
	
3. Dossier publique
	
	Le dossier Web doit être le seul dossier accessible par le client.
	Pour ce faire, vous devez reproduire sur votre serveur apache2 la configuration présentée dans le fichier VirtualHost.conf, et conserver le fichier .htaccess dans ce dossier
	
	Tous les fichiers css, js, images, etc ... seront donc à placer dans ce dossier.
	
4. Logs

	Dans l'exemple, vous pourrez retrouver les fichiers de logs rassemblés dans le dossier Log. Pour modifier leurs noms, comportement ou emplacement, modifier la configuration du VirtualHost et de log4php

	Par défaut, en reproduisant la page d'acceuil Web/index.php, vous retrouverez dans la variable $GLOBALS['logger'] l'objet Logger log4php vous permettant de réaliser vos logs personnalisés vers applications.log
	Utilisez les méthodes trace, debug, warn, etc ... pour logger une ligne de texte selon le niveau de log adapté.
