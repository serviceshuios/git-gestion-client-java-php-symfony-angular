<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\TwigBundle\Debug\TimedTwigEngine;
use Symfony\Component\HttpFoundation\Request;
use NMC\GestionClient\SF2\SimpleBundle\Entity\Client;
use NMC\GestionClient\SF2\SimpleBundle\Form\ClientType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;

class ClientController extends Controller
{
	/**
	 * @Security("has_role('ROLE_ADMIN')")
	 */
    public function showAllAction(){
    	$rep = $this->getDoctrine()->getManager()->getRepository('NMCGestionClientSF2SimpleBundle:Client');
    	$clients = $rep->findAll();
    	$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:show.html.twig', array('clients' => $clients));
    	return new Response($content);
    }
    
    public function showAction($login){
    	$rep = $this->getDoctrine()->getManager()->getRepository('NMCGestionClientSF2SimpleBundle:Client');
    	$client = $rep->find($login);
    	$array = (count($client) != null) ? array('clients' => array($client)) : array('login' => $login);
    	$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:show.html.twig', $array);
    	return new Response($content);
    }
    
    public function showByAction($prop, $value){
    	$rep = $this->getDoctrine()->getManager()->getRepository('NMCGestionClientSF2SimpleBundle:Client');
    	$clients = $rep->findBy(array($prop => $value));
    	$array = array('clients' => $clients, 'by' => true);
    	$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:show.html.twig', $array);
    	return new Response($content);
    }
    
    /**
     * @Security("has_role('ROLE_ADMIN')")
     */
    public function deleteAction($login){
    	$em = $this->getDoctrine()->getManager();
    	$rep = $em->getRepository('NMCGestionClientSF2SimpleBundle:Client');
    	$client = $rep->find($login);
    	if($client != null){
    		$em->remove($client);
    		$em->flush();
    		$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:confirm.html.twig', array('operation' => "supprimé", 'login' => $login));
    	} else {
    		$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:show.html.twig', array('login' => $login));
    	}
    	return new Response($content);
    }
    
    public function updateAction($login, Request $request){
    	$em = $this->getDoctrine()->getManager();
    	$rep = $em->getRepository('NMCGestionClientSF2SimpleBundle:Client');
    	$client = $rep->find($login);
    	$post = $request->request;
    	$client->setCommentaire($post->get('commentaire'));
    	$client->setNom($post->get('nom'));
    	$client->setPrenom($post->get('prenom'));
    	$client->setCommentaire($post->get('commentaire'));
    	$client->setIcon($post->get('icon'));
    	$em->flush();    	 
    	$content = $this->get('templating')->render('NMCGestionClientSF2SimpleBundle:Client:confirm.html.twig', array('operation' => "mis à jour", 'login' => $login));
    	return new Response($content);
    }
    
    /**
     * @Security("has_role('ROLE_ADMIN')")
     */
    public function createAction(Request $request){
    	/*$em = $this->getDoctrine()->getManager();
    	$client = new Client();*/
    	$client = new Client();
    	$form = $this->get('form.factory')->create(new ClientType(), $client);
    	
    	if ($form->handleRequest($request)->isValid()) {
    		$em = $this->getDoctrine()->getManager();
    		$client->getIcon()->upload();
    		$em->persist($client);
    		$em->flush();
    	
    		$request->getSession()->getFlashBag()->add('notice', 'Client bien enregistrée.');
    	
    		return $this->redirect($this->generateUrl('simple_show_client', array('login' => $client->getLogin())));
    	}
    	
    	return $this->render('NMCGestionClientSF2SimpleBundle:Client:create.html.twig', array(
    			'form' => $form->createView(),
    	));
    }
 
    
}
