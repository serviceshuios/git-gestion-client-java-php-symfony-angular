<?php

namespace Library;

/**
 * manager de "prototype"
 * 
 * permet la création d'objets de scope "prototype" (une instance nouvelle par appel)
 */
class Prototype {
	
	/**
	 * nom de la classe managée
	 */
	private $className;
	
	/**
	 * arguments du constructeur
	 */
	private $constructorArgs;

	public function __construct($className, $constructorArgs = null){
		$this->className = $className;
		if(is_array($constructorArgs)){
			$this->constructorArgs = $constructorArgs;
		}
	}
	
	/**
	 * génération d'une instance de la classe managée
	 */
	public function getObject(){
		$class = $this->className;
		if($this->constructorArgs != null){
			return ArrayUtil::constructByArray($this->className, $this->constructorArgs);
		} else {
			 return new $classNamme();
		}
	}
	
	
}

?>