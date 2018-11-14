<?php

namespace NMC\NoteBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction(Request $request)
    {
    	if($request->isMethod('POST')){
    		$msg = array('title' => $request->request->get('title'), 'content' => $request->request->get('content'));
    		$session = $request->getSession();
    		$messages = $session->get('notes');
    		if($messages == null){
    			$session->set('notes', array($msg));
    		} else {
    			array_push($messages, $msg);
    			$session->set('notes', $messages);
    		}
    	}
        return $this->render('NMCNoteBundle:Default:index.html.twig');
    }
    
    public function clearAction(Request $request){
    	$request->getSession()->remove('notes');
    	return $this->redirect($this->generateUrl('nmc_note'));
    }
}
