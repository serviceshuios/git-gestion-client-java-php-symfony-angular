<?php

namespace Library;

use Library\Prototype;

/**
 * IOC
 *
 * cette classe permet de réaliser une inversion de contrôle pour l'instanciation de toutes les classes
 * de notre projet
 * elle permet donc la gestion des instance par configuration XML et l'injection de dépendance
 */
abstract class ObjectFactory {

	/**
	 * tableau associatif id => instance
	 * ensemble des singletons définits dans le fichier
	 */
	private static $objects;

	/**
	 * chargement des objets par configuration XML
	 */
	private static function loadObjects() {

		// chargement du fichier de configuration
		$xml = new \DOMDocument;
		$xml -> load(__DIR__ . '/../Conf/objects.xml');

		// récupération des balises <group> (groupes d'objets d'un même namespace)
		$groups = $xml -> getElementsByTagName('group');

		foreach ($groups as $group) {

			// récupération du namespace du groupe
			$namespace = self::getTagAttribute($group, 'namespace', true);
			$group_name = self::getTagAttribute($group, 'name', true);
			
			$GLOBALS['logger'] -> info("chargement des objets du groupe ".$group_name." pour le namespace " . $namespace);

			// récupération des objets définis dans le groupe
			$objects = $group -> getElementsByTagName('object');
			$GLOBALS['logger'] -> debug((count($objects) + 1) . " objets ont été trouvés");

			foreach ($objects as $object) {

				// récupération de l'id et de la classe de l'objet
				$object_name = self::getTagAttribute($object, 'name');
				$id = $group_name.'/'.$object_name;
				$class = self::getTagAttribute($object, 'class');
				$className = $namespace .'\\'. $class;

				// récupération de la portée de l'objet
				$scope = self::getTagAttribute($object, 'scope', true);

				$args = self::getConstructorArgs($object);

				$GLOBALS['logger'] -> debug("chargement de l'objet " . $id . " instance de la classe " . $className);

				if ($args !== false) {
					if ($scope == "prototype") {
						self::setObject($id, new Prototype($className, $args));
					} else {
						self::setObject($id, ArrayUtil::constructByArray($className, $args));
					}
				} else {
					if ($scope == "prototype") {
						self::setObject($id, new Prototype($className));
					} else {
						self::setObject($id, new $className());
					}
				}
				
				self::setProperties($object, $id);

			}
		}
	}

	/**
	 * récupérer un objet via son id
	 * 
	 * rmq : un retour par référence ici n'est pas absolument nécessaire, étant donné qu'en PHP5, une variable objet ne contient plus l'objet en lui même,
	 * mais un identifiant d'objet. Donc, la variable à laquelle sera affectée la valeur de retour de cette méthode "pointera" bien vers le même objet, au
	 * lieu d'en être une copie. Dans ce cas, une référence semblerait donc apporté plus de complexité pour aucun gain de performance.
	 * voir à ce sujet : http://php.net/manual/fr/language.oop5.references.php et http://php.net/manual/fr/language.references.return.php
	 * 
	 * @param string $id identifiant de l'objet
	 */
	public static function getObject($id) {
		if (self::$objects == null) {
			self::loadObjects();
		}
		$GLOBALS['logger'] -> debug("récupération de l'objet ".$id);
		return self::$objects[$id];
	}

	/**
	 * ajouter un objet
	 * 
	 * @param string $id identifiant de l'objet
	 * @param mixte $object objet à enregistrer
	 * 
	 * @throws Exception si un objet existe déjà pour l'id donné
	 */
	private static function setObject($id, $object) {
		if (!isset(self::$objects[$id])) {
			self::$objects[$id] = $object;
		} else {
			throw new \Exception("Un objet a déjà été créé pour l'id " . $id);
		}
	}

	/**
	 * récupération des valeurs pour l'injection par constructeur
	 * 
	 * @param DOMElement $object balise object sur lequel réaliser l'injection
	 * 
	 * @throws Exception en cas de mauvaise syntaxe dans le fichier
	 * 
	 * @return array tableau des valeurs à injecter (false si aucun)
	 */
	private static function getConstructorArgs($object) {
		$GLOBALS['logger'] -> debug("récupération des arguments du constructeur pour ". $object->getAttribute('name'));
		
		$constructor_args = $object -> getElementsByTagName('constructor-arg');
		$GLOBALS['logger'] -> debug($constructor_args -> length . "arguments trouvés");

		$args_tab = array();
		foreach ($constructor_args as $constructor_arg) {

			$valueOrRef = self::getValueOrRef($constructor_arg);
			$GLOBALS['logger'] -> debug("arg = ".$valueOrRef);
			$name = self::getTagAttribute($constructor_arg, 'name', true);
			if ($name !== '') {
				$GLOBALS['logger'] -> debug("name = ".$name);
				
				if (!isset($args_tab[$name])) {
					$args_tab[$name] = $valueOrRef;
				} else {
					throw new \Exception("Un argument " . $name . " a déjà été enregistré pour le constructeur de " . $className);
				}
			} else {
				array_push($args_tab, $valueOrRef);
				$GLOBALS['logger'] -> debug("pos = ".count($args_tab));
			}

		}

		if (count($args_tab) > 0) {
			return $args_tab;
		}
		return false;
	}

	/**
	 * injection par setter
	 * 
	 * @param mixte $object objet sur lequel réaliser l'injection
	 * 
	 * @throws Exception en cas de mauvaise syntaxe dans le fichier
	 */
	private static function setProperties($object, $id) {
		$properties = $object -> getElementsByTagName('propertie');
		$GLOBALS['logger']->debug($properties->length." propriétés trouvées");

		foreach ($properties as $propertie) {

			$name = self::getTagAttribute($propertie, 'name');
			$setter = "set" . ucfirst($name);
			$ref = self::getTagAttribute($propertie, 'ref', true);
			$value = self::getTagAttribute($propertie, 'value', true);
			if ($ref !== '') {
				if (!isset(self::$objects[$ref])) {
					throw new \Exception("L'objet " . $ref . " n'a pas été définit");
				} else {
					$GLOBALS['logger'] -> debug("injection de l'objet " . $ref . " dans " . $id . " via " . $setter);
					self::getObject($id) -> $setter(self::$objects[$ref]);
				}
			} else if ($value !== '') {
				$GLOBALS['logger'] -> debug("injection de la propriété " . $name . " = " . $value . " dans " . $id . " via " . $setter);
				self::getObject($id) -> $setter($value);
			} else {
				throw new \Exception("Une propriété doit impérativement comporter une référence vers un objet ou une instance");
			}
		}
	}

	/**
	 * Récupération de la valeur d'un attribut d'une balise XML
	 * 
	 * @param DOMElement $tag balise XML
	 * @param string $attribute nom de l'attribut
	 * @param boolean $optionnal (false par défaut) spécifie si l'attribut est "optionnel" ou non (lève une exception si faux et attribut abscent
	 * 
	 * @throws Exception
	 * @return string la valeur de l'attribut
	 */
	private static function getTagAttribute($tag, $attribute, $optionnal = false) {
		if ($tag -> hasAttribute($attribute)) {
			return $tag -> getAttribute($attribute);
		} else if ($optionnal === false) {
			throw new \Exception('Fichier objects.xml : une balise ' . $tag -> tagName . ' doit impérativement posséder un attribut ' . $attribute);
		}
		return '';
	}
	
	/**
	 * récupération de la valeur d'une injection par constructeur
	 * 
	 * @param DOMElement $constructor_arg balise constructor arg dont récupérer la valeur
	 * 
	 * @return mixte la valeur (objet ou string)
	 * 
	 * @throws Exception en cas de référence vers un objet null
	 */
	private static function getValueOrRef($constructor_arg){
		
		if($constructor_arg->hasAttribute('value')){			
			$rslt = $constructor_arg -> getAttribute('value');
			$GLOBALS['logger'] -> debug("value = ".$rslt);
			return $rslt;
		} else {
			$id = self::getTagAttribute($constructor_arg, 'ref');
			$rslt = self::getObject($id);
			if($rslt != null){
				return $rslt;
				$GLOBALS['logger'] -> debug("ref = ".$id);
			} else {
				throw new \Exception("l'objet ".$id." a une valeur null et ne peu donc être référencé");
			}
		}
	}

}
?>
