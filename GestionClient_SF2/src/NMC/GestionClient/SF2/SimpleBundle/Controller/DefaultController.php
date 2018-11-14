<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;


class DefaultController extends Controller {
	
	public function indexAction()
	{
		$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Default:index.html.twig');
		return new Response($content);
	}
}