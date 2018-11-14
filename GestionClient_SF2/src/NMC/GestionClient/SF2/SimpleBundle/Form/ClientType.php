<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class ClientType extends AbstractType {
	public function buildForm(FormBuilderInterface $builder, array $options) {
		$builder
		->add ( 'login', 'text' )
		->add ( 'nom', 'text' )
		->add ( 'prenom', 'text' )
		->add ( 'password', 'repeated', array (
				'type' => 'password',
				'invalid_message' => 'Les mots de passe doivent correspondre',
				'options' => array ('required' => true ),
				'first_options' => array ('label' => 'Mot de passe' ),
				'second_options' => array ('label' => 'Mot de passe (validation)' ),
				'first_name'  => 'pass1',
				'second_name' => 'pass2'
		))
		->add ( 'admin', 'checkbox', array (
				'required' => false,
				'data' => true,
				'label' => 'Faire de cet utilisateur un administrateur'
		))
		->add ( 'expiration', 'datetime', array('label' => "Date d'expiration") )
		->add ( 'commentaire', 'textarea' )
		->add ( 'icon', new IconType())
		->add ( 'save', 'submit', array('label' => 'Valider') );
	}
	
	public function setDefaultOptions(OptionsResolverInterface $resolver) {
		$resolver->setDefaults ( array (
				'data_class' => 'NMC\GestionClient\SF2\SimpleBundle\Entity\Client' 
		) );
	}
	public function getName() {
		return 'simplebundle_client';
	}
}