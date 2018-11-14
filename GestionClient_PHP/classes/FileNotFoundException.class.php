<?php
class FileNotFoundException extends Exception {
	
	public function __construct($path) {
 		parent::__construct(sprintf('The file "%s" does not exist', $path));
	}

}
?>