<?php

namespace Library\Exceptions;

/**
 * Exception à lever en cas de non correspondance entre une chaîne et une regex
 */
class InvalidExpressionException extends \Exception {
	public function __construct($message, $code = 0) {
		parent::__construct($message, $code);
	}

	public function __toString() {
		return $this -> message;
	}
}
?>