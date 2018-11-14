<?php

namespace Library\Exceptions;

/**
 * Exception à lever en cas de fichier inexistant
 */
class FileNotFoundException extends \Exception {
	
	public function __construct($path) {
 		parent::__construct(sprintf('The file "%s" does not exist', $path));
	}

}
?>