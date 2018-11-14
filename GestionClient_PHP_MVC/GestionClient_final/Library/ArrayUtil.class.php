<?php

namespace Library;

abstract class ArrayUtil {

	/**
	 * test si un tableau est associatif
	 *
	 * @param array $array tableau à tester
	 * @return boolean true si associatif
	 */
	public static function is_assoc($array) {
		return (bool)count(array_filter(array_keys($array), 'is_string'));
	}

	/**
	 * retourne une nouvelle instance de classe construite à partir d'un tableau d'arguments
	 * 
	 * si le tableau est associatif, alors le constructeur est géré par hydratation et le tableau est directement passé en tant qu'argument
	 * sinon, ses valeurs sont affectées aux argument du constructeur dans l'ordre du tableau
	 * 
	 * @param string $className nom de la classe
	 * @param array $args arguments
	 * 
	 * @return mixte la nouvelle instance
	 */
	public static function constructByArray($className, array $args) {
		if (!self::is_assoc($args)) {
			$reflect = new \ReflectionClass($className);
			return $reflect -> newInstanceArgs($args);
			/*
			 * Pour PHP 5.6.0
			 * return new $className(...$this->constructorArgs);
			 */
		} else {
			return new $className($args);
		}
	}

}
?>