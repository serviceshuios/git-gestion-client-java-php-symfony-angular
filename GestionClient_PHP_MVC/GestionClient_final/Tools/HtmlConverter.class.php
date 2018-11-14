<?php

namespace Tools;

use Business\Entities\Client;

abstract class HtmlConverter {
	
	public static function clientsToTable(array $clients){
		$rslt = '<table class="sql_table"><tr><th>login</th><th>nom</th><th>prenom</th><th>commentaire</th><th>icone</th><th>expiration</th><th>admin</th></tr>';
		foreach ($clients as $client) {
			$rslt .= self::clientToTableRow($client);
		}
		$rslt .= '</table>';
		return $rslt;
	}
	
	private static function clientToTableRow(Client $client){
		$rslt = '<tr>';
		$array = $client -> toArray();
		unset($array['password']);
		foreach ($array as $key => $value) {
			switch ($key) {
				case 'icon':
					if($value == null || !file_exists($GLOBALS['ROOT'].'/Web/images/client_icons/'.$value)){
						$value = "default.png";
					}
					$value = '<img class="icon" src="/images/client_icons/'.$value.'" alt="icon" />';
					break;
				case 'admin':
					$yesOrNo = ($value) ? 'yes' : 'no';
					if($value){
						$value = 'yes.png';
					} else {
						$value = 'no.png';
					}
					$value = '<img class="yesno" src="/images/'.$value.'" alt="'.$yesOrNo.'" />';
					break;
			}
			$rslt .= '<td>'.$value.'</td>';
		}
		$rslt .= '</tr>';
		return $rslt;
	}
}

?>