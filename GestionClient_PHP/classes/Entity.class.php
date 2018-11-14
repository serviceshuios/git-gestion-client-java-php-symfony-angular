<?php

abstract class Entity {
	
	abstract public function toArray();
	
	abstract function getPk();
}

?>