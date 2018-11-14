<?php

namespace NMC\GestionClient\SF2\SimpleBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Client
 *
 * @ORM\Table(name="Client", uniqueConstraints={@ORM\UniqueConstraint(name="login", columns={"login"}), @ORM\UniqueConstraint(name="Unique_denom", columns={"nom", "prenom"})})
 * @ORM\Entity
 */
class Client
{
    /**
     * @var string
     *
     * @ORM\Column(name="login", type="string", length=50, nullable=false)
     * @ORM\Id
     * @Assert\Length(min=2, minMessage="Le login doit faire au moins {{ limit }} caractères.")
     * @Assert\Regex(pattern="#^[a-z]{2,50}$#i", message="Le login ne doit être composé que de lettres.")
     */
    private $login;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=true)
     * @Assert\Length(min=2, minMessage="Le nom doit faire au moins {{ limit }} caractères.", max=50, maxMessage="Le nom ne doit pas faire plus de {{ limit }} caractères.")
     * @Assert\Regex(pattern="#^[\p{L}- ]{2,50}$#u", message="Le nom ne doit être composé que de lettres, tirets ou espaces")
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=50, nullable=true)
     * @Assert\Length(min=2, minMessage="Le nom doit faire au moins {{ limit }} caractères.")
     * @Assert\Regex(pattern="#^[\p{L}- ]{2,50}$#u", message="Le prenom ne doit être composé que de lettres, tirets ou espaces")
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=32, nullable=false)
     * @Assert\Regex(pattern="#(?=[a-zA-Z0-9]{4,12}$)(?=.*[A-Z])(?=.*[0-9])#", message="Le mot de passe doit n'être composé que de lettres ou chiffres, et doit comporter au moins une lettre majuscule et un chiffre")
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="commentaire", type="text", nullable=true)
     */
    private $commentaire;

   /**
     * @var \Client
     *
     * @ORM\ManyToOne(targetEntity="Icon", cascade={"persist", "remove"})
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="icon", referencedColumnName="id")
     * })
     */
    private $icon;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="expiration", type="datetime", nullable=false)
     * @Assert\DateTime(message="Vous devez spécifier une date et une heure pour la limite d'expiration.")
     */
    private $expiration;

    /**
     * @var boolean
     *
     * @ORM\Column(name="admin", type="boolean", nullable=false)
     */
    private $admin = '0';


    public function __construct(){
    	$this->expiration = (new \DateTime())->modify("+1 year");
    	$this->admin = false;
    }

    /**
     * Get login
     *
     * @return string 
     */
    public function getLogin()
    {
        return $this->login;
    }

    public function setLogin($login)
    {
    	$this->login = $login;
    }

    /**
     * Set nom
     *
     * @param string $nom
     * @return Client
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string 
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set prenom
     *
     * @param string $prenom
     * @return Client
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * Get prenom
     *
     * @return string 
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * Set password
     *
     * @param string $password
     * @return Client
     */
    public function setPassword($password)
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Get password
     *
     * @return string 
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * Set commentaire
     *
     * @param string $commentaire
     * @return Client
     */
    public function setCommentaire($commentaire)
    {
        $this->commentaire = $commentaire;

        return $this;
    }

    /**
     * Get commentaire
     *
     * @return string 
     */
    public function getCommentaire()
    {
        return $this->commentaire;
    }

    /**
     * Set icon
     *
     * @param string $icon
     * @return Client
     */
    public function setIcon($icon)
    {
        $this->icon = $icon;

        return $this;
    }

    /**
     * Get icon
     *
     * @return string 
     */
    public function getIcon()
    {
        return $this->icon;
    }

    /**
     * Set expiration
     *
     * @param \DateTime $expiration
     * @return Client
     */
    public function setExpiration($expiration)
    {
        $this->expiration = $expiration;

        return $this;
    }

    /**
     * Get expiration
     *
     * @return \DateTime 
     */
    public function getExpiration()
    {
        return $this->expiration;
    }

    /**
     * Set admin
     *
     * @param boolean $admin
     * @return Client
     */
    public function setAdmin($admin)
    {
        $this->admin = $admin;

        return $this;
    }

    /**
     * Get admin
     *
     * @return boolean 
     */
    public function getAdmin()
    {
        return $this->admin;
    }
}
