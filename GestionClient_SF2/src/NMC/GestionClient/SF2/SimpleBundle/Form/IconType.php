<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class IconType extends AbstractType {
	public function buildForm(FormBuilderInterface $builder, array $options) {
		$builder->add('name')
        		->add('file');
	}
	public function setDefaultOptions(OptionsResolverInterface $resolver) {
		$resolver->setDefaults ( array (
				'data_class' => 'NMC\GestionClient\SF2\SimpleBundle\Entity\Icon' 
		) );
	}
	public function getName() {
		return 'simplebundle_icon';
	}
}